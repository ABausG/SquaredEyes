package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movies")
data class TMDBMovie(
        @Json(name = "adult") val adult: Boolean = false,
        @Json(name = "backdrop_path") val backdropPath: String? = "",
        @Json(name = "budget") val budget: Int = 0,
        @Json(name = "genres") val genres: List<Genre> = ArrayList(),
        @Json(name = "homepage") val homepage: String? = "",
        @PrimaryKey @Json(name = "id") val id: Int,
        @Json(name = "imdb_id") val imdbId: String? = "",
        @Json(name = "original_language") val originalLanguage: String = "",
        @Json(name = "original_title") val originalTitle: String = "",
        @Json(name = "overview") val overview: String? = "",
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "poster_path") val posterPath: String? = "",
        @Json(name = "production_companies") val productionCompanies: List<ProductionCompany> = ArrayList(),
        @Json(name = "production_countries") val productionCountries: List<ProductionCountry> = ArrayList(),
        @Json(name = "release_date") val releaseDate: String = "",
        @Json(name = "revenue") val revenue: Int = 0,
        @Json(name = "runtime") val runtime: Int = 0,
        @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguage> = ArrayList(),
        @Json(name = "status") val status: String = "",
        @Json(name = "tagline") val tagline: String? = "",
        @Json(name = "title") val title: String = "",
        @Json(name = "video") val video: Boolean = false,
        @Json(name = "vote_average") val voteAverage: Double = 0.0,
        @Json(name = "vote_count") val voteCount: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readInt(),
            parcel.createTypedArrayList(Genre),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.createTypedArrayList(ProductionCompany),
            parcel.createTypedArrayList(ProductionCountry),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.createTypedArrayList(SpokenLanguage),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readDouble(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdropPath)
        parcel.writeInt(budget)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeInt(id)
        parcel.writeString(imdbId)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeTypedList(productionCompanies)
        parcel.writeTypedList(productionCountries)
        parcel.writeString(releaseDate)
        parcel.writeInt(revenue)
        parcel.writeInt(runtime)
        parcel.writeTypedList(spokenLanguages)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBMovie> {
        override fun createFromParcel(parcel: Parcel): TMDBMovie {
            return TMDBMovie(parcel)
        }

        override fun newArray(size: Int): Array<TMDBMovie?> {
            return arrayOfNulls(size)
        }
    }
}