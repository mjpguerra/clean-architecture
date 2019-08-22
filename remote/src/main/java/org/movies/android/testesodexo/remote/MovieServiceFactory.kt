package org.movies.android.testesodexo.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [MovieService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object MovieServiceFactory {

    fun makeBuffeoorService(isDebug: Boolean): MovieService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeMovieService(okHttpClient, makeGson())
    }

    private fun makeMovieService(okHttpClient: OkHttpClient, gson: Gson): MovieService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.trakt.tv/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(MovieService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        var okClient = OkHttpClient.Builder()

        okClient.addInterceptor(httpLoggingInterceptor)
        okClient.connectTimeout(120, TimeUnit.SECONDS)
        okClient.readTimeout(120, TimeUnit.SECONDS)
        okClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("trakt-api-version", "2")
            requestBuilder.header("trakt-api-key", "7124cd814494bb2a5f5fecb24249f762fdcbf2d459eb96ae4eacd7573ded8a65")

            requestBuilder.header("Connection", "close")

            requestBuilder.method(original.method(), original.body())
            chain.proceed(requestBuilder.build())
        })



            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okClient.interceptors().add(interceptor)


        return okClient.build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
          HttpLoggingInterceptor.Level.NONE
        return logging
    }

}