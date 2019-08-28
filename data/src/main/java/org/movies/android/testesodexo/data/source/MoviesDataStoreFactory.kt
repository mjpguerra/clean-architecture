package org.movies.android.testesodexo.data.source

/**
 * Crie uma instância de uma MoviesDataStore
 */
open class MoviesDataStoreFactory(
        private val moviesCacheDataStore: MoviesDataStore,
        private val moviesRemoteDataStore: MoviesDataStore) {

    /**
     * Retorna um DataStore com base em se há ou não conteúdo no cache e no cache
     * não expirou
     */
    open fun retrieveDataStore(isCached: Boolean): MoviesDataStore {
        if (isCached && !moviesCacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Retornar uma instância do armazenamento de dados de cache
     */
    open fun retrieveCacheDataStore(): MoviesDataStore {
        return moviesCacheDataStore
    }

    /**
     * Retornar uma instância do armazenamento de dados remoto
     */
    open fun retrieveRemoteDataStore(): MoviesDataStore {
        return moviesRemoteDataStore
    }

}