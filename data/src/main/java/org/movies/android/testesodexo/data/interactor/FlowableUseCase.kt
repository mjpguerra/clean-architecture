package org.movies.android.testesodexo.data.interactor

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.movies.android.testesodexo.data.executor.PostExecutionThread
import org.movies.android.testesodexo.data.executor.ThreadExecutor

/**
 * Classe abstrata para um UseCase que retorna uma instância de um [Single].
 */
abstract class FlowableUseCase<T, in Params> constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    /**
     * Cria um [Single] que será usado quando o atual [FlowableUseCase] ​​for executado.
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    /**
     * Executa o caso de uso atual.
     */
    open fun execute(params: Params? = null): Flowable<T> {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }
}