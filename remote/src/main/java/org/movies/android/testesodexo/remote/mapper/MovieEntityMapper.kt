package org.movies.android.testesodexo.remote.mapper

import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.remote.model.MovieModel
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Mapear um [MovieModel] para e de uma instância [Movie] quando os dados estiverem se movendo entre
 * isso mais tarde e a camada de dados
 */
open class MovieEntityMapper : EntityMapper<MovieModel, Movie> {

    /**
     * Mapear uma instância de um [MovieModel] para um modelo [Movie]
     */
    override fun mapFromRemote(type: MovieModel): Movie {
        return Movie(generateId(), type.nomeFilme, type.ano, "")
    }


    fun generateId(): String {
        var calCurrentDevice = GregorianCalendar.getInstance()
        var md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(calCurrentDevice.timeInMillis.toString().toByteArray())).toString(6)
    }
}