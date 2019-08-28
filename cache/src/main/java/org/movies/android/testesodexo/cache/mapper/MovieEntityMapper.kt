package org.movies.android.testesodexo.cache.mapper

import org.movies.android.testesodexo.cache.model.CachedMovie
import org.movies.android.testesodexo.data.movies.Movie

/**
 * Mapear uma instância [CachedMovie] de e para uma instância de [Movie] quando os dados estão se movendo entre
 * isso mais tarde e a camada de dados
 */
open class MovieEntityMapper : EntityMapper<CachedMovie, Movie> {

    /**
    * Mapear uma instância de [Movie] para uma instância de [CachedMovie]
    */
    override fun mapToCached(type: Movie): CachedMovie {
        return CachedMovie(null, type.nomeFilme, type.ano, type.avatar)
    }

    /**
     * Mapear uma instância de [CachedMovie] para uma instância de [Movie]
     */
    override fun mapFromCached(type: CachedMovie): Movie {
        return Movie(null, type.name, type.title, type.avatar)
    }

}