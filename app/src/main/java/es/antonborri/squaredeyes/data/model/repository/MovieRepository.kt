package es.antonborri.squaredeyes.data.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.data.room.MovieDao
import es.antonborri.squaredeyes.network.TMDBApi
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(val tmdbApi: TMDBApi, val movieDao: MovieDao) {

    fun getMovie(id : Int) : Observable<TMDBMovie> {
        val observableFromApi = getMovieFromApi(id)
        val observaleFromDb = getMovieFromDb(id)
        return Observable.concatArrayEager(observableFromApi, observaleFromDb)
    }

    private fun getMovieFromApi(id : Int): Observable<TMDBMovie> {
        return tmdbApi.getMovie(id.toString()).doOnNext {
            Log.i("MOVIE FROM TMDB", it.originalTitle)
            movieDao.insertMovie(it)
        }
    }

    private fun getMovieFromDb(id : Int): Observable<TMDBMovie> {
        return movieDao.getMovie(id)
                .doOnNext {
                    Log.i("MOVIE FROM DATABASE", it.originalTitle)
                }
    }

}