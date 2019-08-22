package org.movies.android.testesodexo.ui.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import org.movies.android.testesodexo.data.movies.interactor.GetMovies

class MoviesViewModel(private val getMovies: GetMovies) : ViewModel() {

    private val moviesLiveData: MutableLiveData<MoviesState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getMovies(): LiveData<MoviesState> {
        return moviesLiveData
    }

    fun fetchMovies() {
        moviesLiveData.postValue(MoviesState.Loading)
        disposable = getMovies.execute()
                .subscribe({
                    moviesLiveData.postValue(MoviesState.Success(it))
                }, {
                    moviesLiveData.postValue(MoviesState.Error(it.message))
                })
    }
}