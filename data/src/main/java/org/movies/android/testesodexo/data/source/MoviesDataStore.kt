package org.movies.android.testesodexo.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.data.movies.Movie

/**
 * Interface defining methods for the data operations related to moviesroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface MoviesDataStore {

    fun clearMovies(): Completable

    fun saveMovies(Movies: List<Movie>): Completable

    fun getMovies(): Flowable<List<Movie>>

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}