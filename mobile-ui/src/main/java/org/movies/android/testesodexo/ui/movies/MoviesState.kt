package org.movies.android.testesodexo.ui.movies

import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.ui.model.ResourceState

sealed class MoviesState(val resourceState: ResourceState,
                         val data: List<Movie>? = null,
                         val errorMessage: String? = null) {

    data class Success(private val Movies: List<Movie>): MoviesState(ResourceState.SUCCESS,
            Movies)

    data class Error(private val message: String? = null): MoviesState(ResourceState.SUCCESS,
            errorMessage = message)

    object Loading: MoviesState(ResourceState.LOADING)
}