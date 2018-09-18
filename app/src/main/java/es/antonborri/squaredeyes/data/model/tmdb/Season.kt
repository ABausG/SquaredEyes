package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Season(
        @Json(name = "air_date") val airDate: String?,
        @Json(name = "episode_count") val episodeCount: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String?,
        @Json(name = "overview") val overview: String?,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "season_number") val seasonNumber: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(airDate)
        parcel.writeInt(episodeCount)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeInt(seasonNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Season> {
        override fun createFromParcel(parcel: Parcel): Season {
            return Season(parcel)
        }

        override fun newArray(size: Int): Array<Season?> {
            return arrayOfNulls(size)
        }
    }
}