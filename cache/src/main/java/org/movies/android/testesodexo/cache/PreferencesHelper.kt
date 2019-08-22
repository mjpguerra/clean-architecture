package org.movies.android.testesodexo.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
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
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = moviesPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = moviesPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
