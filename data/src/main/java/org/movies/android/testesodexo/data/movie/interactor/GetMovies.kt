package org.movies.android.testesodexo.data.movies.interactor

import io.reactivex.Flowable
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.executor.PostExecutionThread
import org.movies.android.testesodexo.data.executor.ThreadExecutor
import org.movies.android.testesodexo.data.interactor.FlowableUseCase
import org.movies.android.testesodexo.data.repository.MoviesRepository

open class GetMovies(val moviesRepository: MoviesRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<Movie>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Movie>> {
        return moviesRepository.getMovies()
    }
}