package org.movies.android.testesodexo.cache.mapper

/**
 * Interface para mapeadores de modelos. Ele fornece métodos auxiliares que facilitam
 * Recuperação de modelos de camadas de fontes de dados externas
 *
 * @param <T> o tipo de entrada do modelo em cache
 * @param <T> o tipo de entrada do modelo remoto
 * @param <V> o tipo de retorno do modelo
 */

interface EntityMapper<T, V> {

    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T

}