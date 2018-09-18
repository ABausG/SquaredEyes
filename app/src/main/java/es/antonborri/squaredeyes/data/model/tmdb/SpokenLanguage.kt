package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class SpokenLanguage(
        @Json(name = "iso_639_1") val language_code: String,
        @Json(name = "name") val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(language_code)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SpokenLanguage> {
        override fun createFromParcel(parcel: Parcel): SpokenLanguage {
            return SpokenLanguage(parcel)
        }

        override fun newArray(size: Int): Array<SpokenLanguage?> {
            return arrayOfNulls(size)
        }
    }
}