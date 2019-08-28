package org.movies.android.testesodexo.remote

import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.remote.model.MovieModel
import retrofit2.http.GET

/**
 * Define os métodos abstratos usados ​​para interagir com a API do Movie
 */

interface MovieService {

    @GET("movies/popular")
    fun getMovies(): Flowable<List<MovieModel>>

    class MovieResponse
}