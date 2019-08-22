package org.movies.android.testesodexo.data.source

/**
 * Create an instance of a MoviesDataStore
 */
open class MoviesDataStoreFactory(
        private val moviesCacheDataStore: MoviesDataStore,
        private val moviesRemoteDataStore: MoviesDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): MoviesDataStore {
        if (isCached && !moviesCacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): MoviesDataStore {
        return moviesCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): MoviesDataStore {
        return moviesRemoteDataStore
    }

}