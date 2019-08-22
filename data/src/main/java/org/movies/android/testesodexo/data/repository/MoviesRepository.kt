package org.movies.android.testesodexo.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import org.movies.android.testesodexo.data.movies.Movie

interface MoviesRepository {

    open fun clearMovies(): Completable

    open fun saveMovies(Movies: List<Movie>): Completable

    open fun getMovies(): Flowable<List<Movie>>

}