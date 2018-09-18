package es.antonborri.squaredeyes.data.model.trakt

import com.squareup.moshi.Json

data class Ids(
        @Json(name = "trakt") val trakt: Int,
        @Json(name = "slug") val slug: String,
        @Json(name = "imdb") val imdb: String,
        @Json(name = "tmdb") val tmdb: Int
)