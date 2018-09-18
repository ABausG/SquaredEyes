package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class CreatedBy(
        @Json(name = "id") val id: Int,
        @Json(name = "credit_id") val creditId: String,
        @Json(name = "name") val name: String,
        @Json(name = "gender") val gender: Int?,
        @Json(name = "profile_path") val profilePath: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(creditId)
        parcel.writeString(name)
        parcel.writeInt(gender ?: 0)
        parcel.writeString(profilePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreatedBy> {
        override fun createFromParcel(parcel: Parcel): CreatedBy {
            return CreatedBy(parcel)
        }

        override fun newArray(size: Int): Array<CreatedBy?> {
            return arrayOfNulls(size)
        }
    }
}