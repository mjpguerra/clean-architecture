package org.movies.android.testesodexo.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.movies.android.testesodexo.cache.db.constants.MovieConstants

/**
 * Model used solely for the caching of a movies
 */
@Entity(tableName = MovieConstants.TABLE_NAME)
data class CachedMovie(

        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        val name: String?,
        val title: String?,
        val avatar: String

)