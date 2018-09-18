package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class ProductionCompany(
        @Json(name = "id") val id: Int,
        @Json(name = "logo_path") val logoPath: String?,
        @Json(name = "name") val name: String,
        @Json(name = "origin_country") val originCountry: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(logoPath)
        parcel.writeString(name)
        parcel.writeString(originCountry)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductionCompany> {
        override fun createFromParcel(parcel: Parcel): ProductionCompany {
            return ProductionCompany(parcel)
        }

        override fun newArray(size: Int): Array<ProductionCompany?> {
            return arrayOfNulls(size)
        }
    }
}