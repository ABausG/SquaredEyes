package es.antonborri.squaredeyes.data.model.trakt

import com.squareup.moshi.Json

data class TraktMovieEnvelope(
        @Json(name = "watchers") val watchers: Int?,
        @Json(name = "watcher_count") val watcherCount: Int?,
        @Json(name = "play_count") val playCount: Int?,
        @Json(name = "collected_count") val collectedCount: Int?,
        @Json(name = "revenue") val revenue: Int?,
        @Json(name = "list_count") val list_count: Int?,
        @Json(name = "updated_at") val updatedAt: String?,
        @Json(name = "show") val movie: TraktMovie
)