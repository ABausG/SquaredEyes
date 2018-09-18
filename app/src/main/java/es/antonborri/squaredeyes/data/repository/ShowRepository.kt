package es.antonborri.squaredeyes.data.repository

import android.util.Log
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow
import es.antonborri.squaredeyes.data.room.ShowDao
import es.antonborri.squaredeyes.network.TMDBApi
import io.reactivex.Observable
import javax.inject.Inject

class ShowRepository @Inject constructor(val tmdbApi: TMDBApi, val showDao: ShowDao) {

    fun getShow(id : Int) : Observable<TMDBShow> {
        val observableFromApi = getShowFromApi(id)
        val observableFromDb = getShowFromDb(id)
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    private fun getShowFromApi(id : Int): Observable<TMDBShow> {
        return tmdbApi.getShow(id.toString()).doOnNext {
            Log.i("MOVIE FROM TMDB", it.name)
            showDao.insertShow(it)
        }
    }

    private fun getShowFromDb(id : Int): Observable<TMDBShow> {
        return showDao.getShow(id).doOnNext {
            Log.i("MOVIE FROM TMDB", it.name)
        }
    }

}