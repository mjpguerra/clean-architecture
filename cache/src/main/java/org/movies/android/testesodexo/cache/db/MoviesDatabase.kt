package org.movies.android.testesodexo.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.movies.android.testesodexo.cache.dao.CachedMovieDao
import org.movies.android.testesodexo.cache.model.CachedMovie

@Database(entities = [CachedMovie::class], version = 2)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun cachedMovieDao(): CachedMovieDao

    private var INSTANCE: MoviesDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): MoviesDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MoviesDatabase::class.java, "Movies.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }


}