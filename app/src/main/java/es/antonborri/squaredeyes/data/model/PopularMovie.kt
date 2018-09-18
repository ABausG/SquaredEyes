package es.antonborri.squaredeyes.data.model

import com.squareup.moshi.Json

data class PopularMovie(
        @Json(name = "title") val title: String,
        @Json(name = "year") val year: Int,
        @Json(name = "ids") val ids: Ids
)