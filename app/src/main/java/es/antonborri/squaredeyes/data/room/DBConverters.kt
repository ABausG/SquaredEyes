package es.antonborri.squaredeyes.data.room

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import es.antonborri.squaredeyes.data.model.tmdb.*

class DBConverters {
    private val moshi: Moshi = Moshi.Builder().build()


    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        val genreList = Types.newParameterizedType(List::class.java, Genre::class.java)
        val genreListAdapter = moshi.adapter<List<Genre>>(genreList)
        return genreListAdapter.toJson(genres)
    }

    @TypeConverter
    fun toGenres(genres: String): List<Genre> {
        val genreList = Types.newParameterizedType(List::class.java, Genre::class.java)
        val genreListAdapter = moshi.adapter<List<Genre>>(genreList)
        return genreListAdapter.fromJson(genres)!!
    }

    @TypeConverter
    fun fromProductionCountries(productionCountries: List<ProductionCountry>): String {
        val productionCountryList = Types.newParameterizedType(List::class.java, ProductionCountry::class.java)
        val productionCountryListAdapter = moshi.adapter<List<ProductionCountry>>(productionCountryList)
        return productionCountryListAdapter.toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountries(productionCountries: String): List<ProductionCountry> {
        val productionCountryList = Types.newParameterizedType(List::class.java, ProductionCountry::class.java)
        val productionCountryListAdapter = moshi.adapter<List<ProductionCountry>>(productionCountryList)
        return productionCountryListAdapter.fromJson(productionCountries)!!
    }

    @TypeConverter
    fun fromProductionCompanies(productionCompanies: List<ProductionCompany>): String {
        val productionCompanyList = Types.newParameterizedType(List::class.java, ProductionCompany::class.java)
        val productionCompanyListAdapter = moshi.adapter<List<ProductionCompany>>(productionCompanyList)
        return productionCompanyListAdapter.toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompanies: String): List<ProductionCompany> {
        val productionCompanyList = Types.newParameterizedType(List::class.java, ProductionCompany::class.java)
        val productionCompanyListAdapter = moshi.adapter<List<ProductionCompany>>(productionCompanyList)
        return productionCompanyListAdapter.fromJson(productionCompanies)!!
    }

    @TypeConverter
    fun fromSpokenLanguages(spokenLanguages: List<SpokenLanguage>): String {
        val spokenLanguageList = Types.newParameterizedType(List::class.java, SpokenLanguage::class.java)
        val spokenLanguageListAdapter = moshi.adapter<List<SpokenLanguage>>(spokenLanguageList)
        return spokenLanguageListAdapter.toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLanguages: String): List<SpokenLanguage> {
        val spokenLanguageList = Types.newParameterizedType(List::class.java, SpokenLanguage::class.java)
        val spokenLanguageListAdapter = moshi.adapter<List<SpokenLanguage>>(spokenLanguageList)
        return spokenLanguageListAdapter.fromJson(spokenLanguages)!!
    }

    @TypeConverter
    fun fromLanguages(languages: List<String>): String {
        val languageList = Types.newParameterizedType(List::class.java, String::class.java)
        val languageListAdapter = moshi.adapter<List<String>>(languageList)
        return languageListAdapter.toJson(languages)
    }

    @TypeConverter
    fun toLanguages(languages: String): List<String> {
        val languageList = Types.newParameterizedType(List::class.java, String::class.java)
        val languageListAdapter = moshi.adapter<List<String>>(languageList)
        return languageListAdapter.fromJson(languages)!!
    }

    @TypeConverter
    fun fromNetworks(networks: List<Network>): String {
        val networkList = Types.newParameterizedType(List::class.java, Network::class.java)
        val networkListAdapter = moshi.adapter<List<Network>>(networkList)
        return networkListAdapter.toJson(networks)
    }

    @TypeConverter
    fun toNetworks(networks: String): List<Network> {
        val networkList = Types.newParameterizedType(List::class.java, Network::class.java)
        val networkListAdapter = moshi.adapter<List<Network>>(networkList)
        return networkListAdapter.fromJson(networks)!!
    }

    @TypeConverter
    fun fromLastEpisodeToAir(lastEpisodeToAir: LastEpisodeToAir): String {
        val lastEpisodeToAirListAdapter = moshi.adapter<LastEpisodeToAir>(LastEpisodeToAir::class.java)
        return lastEpisodeToAirListAdapter.toJson(lastEpisodeToAir)
    }

    @TypeConverter
    fun toLastEpisodeToAir(lastEpisodeToAir: String): LastEpisodeToAir {
        val lastEpisodeToAirListAdapter = moshi.adapter<LastEpisodeToAir>(LastEpisodeToAir::class.java)
        return lastEpisodeToAirListAdapter.fromJson(lastEpisodeToAir)!!
    }

    @TypeConverter
    fun fromCreatedBys(createdBys: List<CreatedBy>): String {
        val createdByList = Types.newParameterizedType(List::class.java, CreatedBy::class.java)
        val createdByListAdapter = moshi.adapter<List<CreatedBy>>(createdByList)
        return createdByListAdapter.toJson(createdBys)
    }

    @TypeConverter
    fun toCreatedBys(createdBys: String): List<CreatedBy> {
        val createdByList = Types.newParameterizedType(List::class.java, CreatedBy::class.java)
        val createdByListAdapter = moshi.adapter<List<CreatedBy>>(createdByList)
        return createdByListAdapter.fromJson(createdBys)!!
    }

    @TypeConverter
    fun fromSeasons(seasons: List<Season>): String {
        val seasonList = Types.newParameterizedType(List::class.java, Season::class.java)
        val seasonListAdapter = moshi.adapter<List<Season>>(seasonList)
        return seasonListAdapter.toJson(seasons)
    }

    @TypeConverter
    fun toSeasons(seasons: String): List<Season> {
        val seasonList = Types.newParameterizedType(List::class.java, Season::class.java)
        val seasonListAdapter = moshi.adapter<List<Season>>(seasonList)
        return seasonListAdapter.fromJson(seasons)!!
    }
    
    
}