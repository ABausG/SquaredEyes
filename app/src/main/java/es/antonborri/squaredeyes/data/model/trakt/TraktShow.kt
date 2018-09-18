package es.antonborri.squaredeyes.data.model.trakt

import com.squareup.moshi.Json

data class TraktShow(
        @Json(name = "title") val title: String,
        @Json(name = "year") val year: Int,
        @Json(name = "ids") val ids: Ids
)