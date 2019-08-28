package org.movies.android.testesodexo.data.interactor

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable


/**
 * Classe base [SingleObserver] padrão para definir
 */
open class BaseSingleObserver<T> : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) { }

    override fun onSuccess(t: T) { }

    override fun onError(exception: Throwable) { }

}