package org.movies.android.testesodexo.cache

import android.content.Context
import android.content.SharedPreferences


/**
* Classe auxiliar de preferências gerais, usada para armazenar valores de preferência usando a API de preferência
*/
open class PreferencesHelper(context: Context) {

    companion object {
        private val PREF_movies_PACKAGE_NAME = "org.movies.android.testesodexo.preferences"

        private val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val moviesPref: SharedPreferences

    init {
        moviesPref = context.getSharedPreferences(PREF_movies_PACKAGE_NAME, Context.MODE_PRIVATE)
    }


    /**
    * Armazene e recupere a última vez que os dados foram armazenados em cache
    */
    var lastCacheTime: Long
        get() = moviesPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = moviesPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
