package org.movies.android.testesodexo.remote

import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.remote.model.MovieModel
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Movie API
 */
interface MovieService {

    @GET("movies/popular")
    fun getMovies(): Flowable<List<MovieModel>>

    class MovieResponse
}