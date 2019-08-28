package org.movies.android.testesodexo.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.movies.android.testesodexo.cache.db.constants.MovieConstants


/**
* Modelo usado apenas para o armazenamento em cache de MOVIE
*/
@Entity(tableName = MovieConstants.TABLE_NAME)
data class CachedMovie(

        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        val name: String?,
        val title: String?,
        val avatar: String

)