package org.movies.android.testesodexo.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Representation for a [MovieModel] fetched from the API
 */
class MovieModel(

        @SerializedName("title")
        @Expose
        var  nomeFilme : String?,

        @SerializedName("year")
        @Expose
        var ano : String?

)
