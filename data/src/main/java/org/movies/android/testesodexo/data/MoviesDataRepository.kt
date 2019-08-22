package org.movies.android.testesodexo.data

import io.reactivex.Completable
import io.reactivex.Flowable
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.repository.MoviesRepository
import org.movies.android.testesodexo.data.source.MoviesDataStoreFactory

/**
 * Provides an implementation of the [MoviesRepository] interface for communicating to and from
 * data sources
 */
open class MoviesDataRepository(private val factory: MoviesDataStoreFactory) :
        MoviesRepository {

    override fun clearMovies(): Completable {
        return factory.retrieveCacheDataStore().clearMovies()
    }

    override fun saveMovies(Movies: List<Movie>): Completable {
        return factory.retrieveCacheDataStore().saveMovies(Movies)
    }

    override fun getMovies(): Flowable<List<Movie>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getMovies()
                }
                .flatMap {
                    saveMovies(it).toSingle { it }.toFlowable()
                }
    }

}