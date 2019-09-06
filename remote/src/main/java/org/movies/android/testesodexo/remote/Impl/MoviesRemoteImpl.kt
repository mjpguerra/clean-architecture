package org.movies.android.testesodexo.remote.Impl

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.data.source.MoviesDataStore
import org.movies.android.testesodexo.remote.Service
import org.movies.android.testesodexo.remote.mapper.MovieEntityMapper

/**
 * Implementação remota para recuperar instâncias do filme. Esta classe implementa o
 * [MovieRemote] da camada de dados, pois é a responsabilidade de camadas para definir o
 * operações nas quais as camadas de implementação do armazenamento de dados podem ser executadas.
 */
class MoviesRemoteImpl constructor(private val MovieService: Service, private val entityMapper: MovieEntityMapper) :MoviesDataStore {

    /**
     * Recupere uma lista de instâncias de [Movie] do [Service].
     */
    override fun getMovies(): Flowable<List<Movie>> {
        return MovieService.getMovies()
                .map {

                    val entities = mutableListOf<Movie>()

                    it.forEach {
                        entities.add(entityMapper.mapFromRemote(it))
                    }

                    entities
                }
    }

    override fun clearMovies(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovies(Movies: List<Movie>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}