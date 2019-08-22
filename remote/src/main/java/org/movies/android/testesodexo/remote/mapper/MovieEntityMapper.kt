package org.movies.android.testesodexo.remote.mapper

import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.remote.model.MovieModel
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Map a [MovieModel] to and from a [Movie] instance when data is moving between
 * this later and the Data layer
 */
open class MovieEntityMapper : EntityMapper<MovieModel, Movie> {

    /**
     * Map an instance of a [MovieModel] to a [Movie] model
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