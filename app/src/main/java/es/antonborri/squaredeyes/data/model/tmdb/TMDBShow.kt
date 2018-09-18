package es.antonborri.squaredeyes.data.model.tmdb

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity (tableName = "shows")
data class TMDBShow(
        @Json(name = "backdrop_path") val backdropPath: String?,
        @Json(name = "created_by") val createdBy: List<CreatedBy>?,
        @Json(name = "first_air_date") val firstAirDate: String?,
        @Json(name = "genres") val genres: List<Genre>,
        @Json(name = "homepage") val homepage: String,
        @PrimaryKey @Json(name = "id") val id: Int,
        @Json(name = "in_production") val inProduction: Boolean,
        @Json(name = "languages") val languages: List<String>?,
        @Json(name = "last_air_date") val lastAirDate: String?,
        @Json(name = "last_episode_to_air") val lastEpisodeToAir: LastEpisodeToAir?,
        @Json(name = "name") val name: String,
        @Json(name = "networks") val networks: List<Network>,
        @Json(name = "number_of_episodes") val numberOfEpisodes: Int,
        @Json(name = "number_of_seasons") val numberOfSeasons: Int,
        @Json(name = "origin_country") val originCountry: List<String>,
        @Json(name = "original_language") val originalLanguage: String,
        @Json(name = "original_name") val originalName: String,
        @Json(name = "overview") val overview: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "production_companies") val productionCompanies: List<ProductionCompany>,
        @Json(name = "seasons") val seasons: List<Season>,
        @Json(name = "status") val status: String,
        @Json(name = "type") val type: String,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "vote_count") val voteCount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArrayList(CreatedBy),
            parcel.readString(),
            parcel.createTypedArrayList(Genre),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readParcelable(LastEpisodeToAir::class.java.classLoader)!!,
            parcel.readString(),
            parcel.createTypedArrayList(Network),
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.createTypedArrayList(ProductionCompany),
            parcel.createTypedArrayList(Season),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backdropPath)
        parcel.writeTypedList(createdBy)
        parcel.writeString(firstAirDate)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeInt(id)
        parcel.writeByte(if (inProduction) 1 else 0)
        parcel.writeStringList(languages)
        parcel.writeString(lastAirDate)
        parcel.writeParcelable(lastEpisodeToAir, flags)
        parcel.writeString(name)
        parcel.writeTypedList(networks)
        parcel.writeInt(numberOfEpisodes)
        parcel.writeInt(numberOfSeasons)
        parcel.writeStringList(originCountry)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalName)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeTypedList(productionCompanies)
        parcel.writeTypedList(seasons)
        parcel.writeString(status)
        parcel.writeString(type)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBShow> {
        override fun createFromParcel(parcel: Parcel): TMDBShow {
            return TMDBShow(parcel)
        }

        override fun newArray(size: Int): Array<TMDBShow?> {
            return arrayOfNulls(size)
        }
    }
}