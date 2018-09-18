package es.antonborri.squaredeyes.data.model.trakt

import com.squareup.moshi.Json

data class TrendingTraktShow (
        @Json(name = "watchers") val meta: Int,
        @Json(name = "show") val show: TraktShow
)