package org.movies.android.testesodexo.cache.mapper

import org.movies.android.testesodexo.cache.model.CachedMovie
import org.movies.android.testesodexo.data.movies.Movie

/**
 * Map a [CachedMovie] instance to and from a [Movie] instance when data is moving between
 * this later and the Data layer
 */
open class MovieEntityMapper :
        EntityMapper<CachedMovie, Movie> {

    /**
     * Map a [Movie] instance to a [CachedMovie] instance
     */
    override fun mapToCached(type: Movie): CachedMovie {
        return CachedMovie(null, type.nomeFilme, type.ano, type.avatar)
    }

    /**
     * Map a [CachedMovie] instance to a [Movie] instance
     */
    override fun mapFromCached(type: CachedMovie): Movie {
        return Movie(null, type.name, type.title, type.avatar)
    }

}