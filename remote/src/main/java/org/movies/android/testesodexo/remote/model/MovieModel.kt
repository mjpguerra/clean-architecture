package org.movies.android.testesodexo.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Representação para um [MovieModel] obtido da API
 */
class MovieModel(

        @SerializedName("title")
        @Expose
        var  nomeFilme : String?,

        @SerializedName("year")
        @Expose
        var ano : String?,

        @SerializedName("ids")
        @Expose
        var ids : Ids?


){

        inner class Ids (
                @SerializedName("trakt")
                @Expose
                var trakt: String?,

                @SerializedName("slug")
                @Expose
                var slug: String?,

                @SerializedName("imdb")
                @Expose
                var imdb: String?,

                @SerializedName("tmdb")
                @Expose
                var tmdb: String?
        )

}
