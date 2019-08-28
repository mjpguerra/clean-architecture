package org.movies.android.testesodexo.data.interactor

import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import org.movies.android.testesodexo.data.executor.PostExecutionThread
import org.movies.android.testesodexo.data.executor.ThreadExecutor

/**
 * Classe abstrata para um UseCase que retorna uma instância de um [Completable].
 */
abstract class CompletableUseCase<in Params> protected constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private val subscription = Disposables.empty()

    /**
     * Cria um [Completable] que será usado quando o atual [CompletableUseCase] ​​for executado.
     */
    protected abstract fun buildUseCaseObservable(params: Params): Completable

    /**
     * Executa o caso de uso atual.
     */
    fun execute(params: Params): Completable {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }

    /**
     *Anula a subscrição de [Disposable] atual.
     */
    fun unsubscribe() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

}