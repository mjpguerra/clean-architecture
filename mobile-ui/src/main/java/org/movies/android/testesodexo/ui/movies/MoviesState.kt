package org.movies.android.testesodexo.ui.movies

import org.movies.android.testesodexo.data.movies.Movie

sealed class MoviesState(val data: List<Movie>? = null) {

    data class Success(private val Movies: List<Movie>): MoviesState(Movies)

    data class Error(private val message: String? = null): MoviesState()

    object Loading: MoviesState()
}