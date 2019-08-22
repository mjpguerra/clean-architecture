package org.movies.android.testesodexo.ui.di

import android.arch.persistence.room.Room
import org.movies.android.testesodexo.cache.MoviesCacheImpl
import org.movies.android.testesodexo.cache.PreferencesHelper
import org.movies.android.testesodexo.cache.db.MoviesDatabase
import org.movies.android.testesodexo.cache.mapper.MovieEntityMapper
import org.movies.android.testesodexo.data.MoviesDataRepository
import org.movies.android.testesodexo.data.movies.interactor.GetMovies
import org.movies.android.testesodexo.data.executor.JobExecutor
import org.movies.android.testesodexo.data.executor.PostExecutionThread
import org.movies.android.testesodexo.data.executor.ThreadExecutor
import org.movies.android.testesodexo.data.repository.MoviesRepository
import org.movies.android.testesodexo.data.source.MoviesDataStore
import org.movies.android.testesodexo.data.source.MoviesDataStoreFactory
import org.movies.android.testesodexo.remote.MoviesRemoteImpl
import org.movies.android.testesodexo.remote.MovieServiceFactory
import org.movies.android.testesodexo.ui.BuildConfig
import org.movies.android.testesodexo.ui.UiThread
import org.movies.android.testesodexo.ui.movies.MoviesAdapter
import org.movies.android.testesodexo.ui.movies.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override=true) {

    single { PreferencesHelper(androidContext()) }

    factory { org.movies.android.testesodexo.remote.mapper.MovieEntityMapper() }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    single { Room.databaseBuilder(androidContext(),
            MoviesDatabase::class.java, "Movies.db")
            .build() }
    factory {
        get<MoviesDatabase>().cachedMovieDao()
    }

    factory<MoviesDataStore>("remote") {
        MoviesRemoteImpl(get(), get())
    }

    factory<MoviesDataStore>("local") {
        MoviesCacheImpl(get(), get(), get())
    }

    factory {
        MoviesDataStoreFactory(get("local"), get("remote"))
    }

    factory {
        MovieEntityMapper()
    }
    factory {
        MovieServiceFactory.makeBuffeoorService(BuildConfig.DEBUG)
    }

    factory<MoviesRepository> {
        MoviesDataRepository(get())
    }
}

val moviesModule = module("movies", override = true) {
    factory { MoviesAdapter() }
    factory { GetMovies(get(), get(), get()) }
    viewModel { MoviesViewModel(get()) }
}