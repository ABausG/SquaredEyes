package es.antonborri.squaredeyes.network

import es.antonborri.squaredeyes.data.model.trakt.TraktMovie
import es.antonborri.squaredeyes.data.model.trakt.TraktShowEnvelope
import io.reactivex.Observable
import retrofit2.http.GET

interface TraktApi {

    @GET("movies/popular")
    fun getPopularMovies(): Observable<List<TraktMovie>>

    @GET("shows/trending")
    fun getTrendingShows(): Observable<List<TraktShowEnvelope>>
}