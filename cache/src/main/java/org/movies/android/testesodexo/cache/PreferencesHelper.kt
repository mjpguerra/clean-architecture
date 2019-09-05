package org.movies.android.testesodexo.cache

import android.content.Context
import android.content.SharedPreferences
import ch.liip.sweetpreferences.SweetPreferences


/**
* Classe auxiliar de preferências gerais, usada para armazenar valores de preferência usando a API de preferência
*/
open class PreferencesHelper(context: Context) {

    private val sweetPreferences: SweetPreferences = SweetPreferences.Builder().withDefaultSharedPreferences(context).build()

    companion object {
        private val PREF_KEY_LAST_CACHE = "last_cache"
    }

    var lastCacheTime: Long by sweetPreferences.delegate(0)

    init {

    }



}
