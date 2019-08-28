package org.movies.android.testesodexo.remote.mapper

/**
 * Interface para mapeadores de modelos. Ele fornece métodos auxiliares que facilitam
 * Recuperação de modelos de camadas de fontes de dados externas
 *
 * @param <M> o tipo de entrada do modelo remoto
 * @param <E> o tipo de saída do modelo de entidade
 **/
interface EntityMapper<in M, out E> {

    fun mapFromRemote(type: M): E

}