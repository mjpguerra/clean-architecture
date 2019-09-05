package org.movies.android.testesodexo.ui.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_movies.progress
import kotlinx.android.synthetic.main.activity_movies.recycler_movies
import kotlinx.android.synthetic.main.activity_movies.view_empty
import kotlinx.android.synthetic.main.activity_movies.view_error
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.ui.R
import org.movies.android.testesodexo.ui.widget.empty.EmptyListener
import org.movies.android.testesodexo.ui.widget.error.ErrorListener
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity: AppCompatActivity() {

    private val moviesAdapter: MoviesAdapter by inject()

    val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        bindScope(getCurrentScope())

        setupmoviesRecycler()
        setupViewListeners()

        moviesViewModel.getMovies().observe(this, Observer<MoviesState> {
                    if (it != null)
                        this.handleDataState(it)
        })

        moviesViewModel.fetchMovies()
    }

    private fun setupmoviesRecycler() {
        recycler_movies.layoutManager = LinearLayoutManager(this)
        recycler_movies.adapter = moviesAdapter
    }

    private fun handleDataState(moviesState: MoviesState) {
        when (moviesState) {
            is MoviesState.Loading -> setupScreenForLoadingState()
            is MoviesState.Success -> setupScreenForSuccess(moviesState.data)
            is MoviesState.Error -> setupScreenForError()
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_movies.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: List<Movie>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data!= null && data.isNotEmpty()) {
            updateListView(data)
            recycler_movies.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(movies: List<Movie>) {
        moviesAdapter.movies = movies
        moviesAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError() {
        progress.visibility = View.GONE
        recycler_movies.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            moviesViewModel.fetchMovies()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            moviesViewModel.fetchMovies()
        }
    }

}