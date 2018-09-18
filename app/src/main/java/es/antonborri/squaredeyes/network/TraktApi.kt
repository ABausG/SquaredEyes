package es.antonborri.squaredeyes.network

import es.antonborri.squaredeyes.data.model.PopularMovie
import io.reactivex.Observable
import retrofit2.http.GET

interface TraktApi {

    @GET("/movies/popular")
    fun getPopularMovies() : Observable<List<PopularMovie>>
}