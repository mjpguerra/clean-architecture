package org.movies.android.testesodexo.remote

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.source.MoviesDataStore
import org.movies.android.testesodexo.remote.mapper.MovieEntityMapper

/**
 * Remote implementation for retrieving Movie instances. This class implements the
 * [MovieRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MoviesRemoteImpl constructor(private val MovieService: MovieService, private val entityMapper: MovieEntityMapper) :MoviesDataStore {

    /**
     * Retrieve a list of [Movie] instances from the [MovieService].
     */
    override fun getMovies(): Flowable<List<Movie>> {
        return MovieService.getMovies()
                .map {

                    val entities = mutableListOf<Movie>()

                    it.forEach {
                        entities.add(entityMapper.mapFromRemote(it))
                    }

                    entities
                }
    }

    override fun clearMovies(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovies(Movies: List<Movie>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}