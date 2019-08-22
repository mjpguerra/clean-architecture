package org.movies.android.testesodexo.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.cache.db.MoviesDatabase
import org.movies.android.testesodexo.cache.mapper.MovieEntityMapper
import org.movies.android.testesodexo.cache.model.CachedMovie
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.source.MoviesDataStore

/**
 * Cached implementation for retrieving and saving Movie instances. This class implements the
 * [MovieCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MoviesCacheImpl constructor(val MoviesDatabase: MoviesDatabase, private val entityMapper: MovieEntityMapper, private val preferencesHelper: PreferencesHelper) : MoviesDataStore {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    /**
     * Retrieve an instance from the database, used for tests.
     */
    internal fun getDatabase(): MoviesDatabase {
        return MoviesDatabase
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearMovies(): Completable {
        return Completable.defer {
            MoviesDatabase.cachedMovieDao().clearMovies()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [Movie] instances to the database.
     */
    override fun saveMovies(Movies: List<Movie>): Completable {
        return Completable.defer {
            Movies.forEach {
                MoviesDatabase.cachedMovieDao().insertMovie(
                        entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [Movie] instances from the database.
     */
    override fun getMovies(): Flowable<List<Movie>> {
        return Flowable.defer {
            Flowable.just(MoviesDatabase.cachedMovieDao().getMovies())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedMovie] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(MoviesDatabase.cachedMovieDao().getMovies().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}