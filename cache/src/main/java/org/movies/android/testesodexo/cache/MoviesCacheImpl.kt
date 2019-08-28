package org.movies.android.testesodexo.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.cache.db.MoviesDatabase
import org.movies.android.testesodexo.cache.mapper.MovieEntityMapper
import org.movies.android.testesodexo.cache.model.CachedMovie
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.source.MoviesDataStore

/**
 * Implementação em cache para recuperar e salvar instâncias do Movie. Esta classe implementa o
 * [MovieCache] da camada de dados, pois é a responsabilidade de camadas para definir o
 * operações nas quais as camadas de implementação do armazenamento de dados podem ser executadas.
 */
class MoviesCacheImpl constructor(val MoviesDatabase: MoviesDatabase, private val entityMapper: MovieEntityMapper, private val preferencesHelper: PreferencesHelper) : MoviesDataStore {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    /**
     * Recupere uma instância do banco de dados, usada para testes.
     */
    internal fun getDatabase(): MoviesDatabase {
        return MoviesDatabase
    }

    /**
     * Remova todos os dados de todas as tabelas no banco de dados.
     */
    override fun clearMovies(): Completable {
        return Completable.defer {
            MoviesDatabase.cachedMovieDao().clearMovies()
            Completable.complete()
        }
    }

    /**
     * Salve a lista dada de instâncias [Movie] no banco de dados.
     */
    override fun saveMovies(Movies: List<Movie>): Completable {
        return Completable.defer {
            Movies.forEach {
                MoviesDatabase.cachedMovieDao().insertMovie(
                        entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    /**
     * Recupere uma lista de instâncias [Movie] do banco de dados.
     */
    override fun getMovies(): Flowable<List<Movie>> {
        return Flowable.defer {
            Flowable.just(MoviesDatabase.cachedMovieDao().getMovies())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Verifique se há instâncias de [CachedMovie] armazenadas no cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(MoviesDatabase.cachedMovieDao().getMovies().isNotEmpty())
        }
    }

    /**
     * Defina um ponto no tempo em que o cache foi atualizado pela última vez.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Verifique se os dados em cache atuais excedem o tempo definido de [EXPIRATION_TIME].
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }


    /**
    * Entre em millis, a última vez que o cache foi acessado.
    */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}