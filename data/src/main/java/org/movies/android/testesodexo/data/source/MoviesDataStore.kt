package org.movies.android.testesodexo.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.data.movies.Movie

/**
 * Métodos de definição de interface para as operações de dados relacionadas a filmes.
 * Isso deve ser implementado por camadas de fontes de dados externas, definindo os requisitos para o
 * operações que precisam ser implementadas
 */
interface MoviesDataStore {

    fun clearMovies(): Completable

    fun saveMovies(Movies: List<Movie>): Completable

    fun getMovies(): Flowable<List<Movie>>

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}