package org.movies.android.testesodexo.ui

import android.app.Application
import org.movies.android.testesodexo.ui.di.applicationModule
import org.movies.android.testesodexo.ui.di.moviesModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule, moviesModule))
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
