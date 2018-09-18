package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class LastEpisodeToAir(
        @Json(name = "air_date") val airDate: String?,
        @Json(name = "episode_number") val episodeNumber: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String?,
        @Json(name = "overview") val overview: String?,
        @Json(name = "production_code") val productionCode: String?,
        @Json(name = "season_number") val seasonNumber: Int,
        @Json(name = "show_id") val showId: Int,
        @Json(name = "still_path") val stillPath: String?,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "vote_count") val voteCount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(airDate)
        parcel.writeInt(episodeNumber)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeString(productionCode)
        parcel.writeInt(seasonNumber)
        parcel.writeInt(showId)
        parcel.writeString(stillPath)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LastEpisodeToAir> {
        override fun createFromParcel(parcel: Parcel): LastEpisodeToAir {
            return LastEpisodeToAir(parcel)
        }

        override fun newArray(size: Int): Array<LastEpisodeToAir?> {
            return arrayOfNulls(size)
        }
    }
}