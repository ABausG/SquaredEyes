package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class ProductionCountry(
        @Json(name = "iso_3166_1") val country_code: String,
        @Json(name = "name") val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country_code)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductionCountry> {
        override fun createFromParcel(parcel: Parcel): ProductionCountry {
            return ProductionCountry(parcel)
        }

        override fun newArray(size: Int): Array<ProductionCountry?> {
            return arrayOfNulls(size)
        }
    }
}