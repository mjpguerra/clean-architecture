package org.movies.android.testesodexo.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import org.movies.android.testesodexo.cache.db.constants.MovieConstants
import org.movies.android.testesodexo.cache.model.CachedMovie

@Dao
abstract class CachedMovieDao {

    @Query(MovieConstants.QUERY_MovieS)
    abstract fun getMovies(): List<CachedMovie>

    @Query(MovieConstants.DELETE_ALL_MovieS)
    abstract fun clearMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(cachedMovie: CachedMovie)

}