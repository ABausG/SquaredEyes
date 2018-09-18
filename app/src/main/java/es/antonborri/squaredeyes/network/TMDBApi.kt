package es.antonborri.squaredeyes.network

import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") id: String) : Observable<TMDBMovie>
}