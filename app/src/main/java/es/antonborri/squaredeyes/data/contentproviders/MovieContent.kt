package es.antonborri.squaredeyes.data.contentproviders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.data.model.trakt.TraktMovie
import es.antonborri.squaredeyes.data.repository.MovieRepository
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class MovieContent(private val traktApi: TraktApi, private val movieRepository: MovieRepository) {

    private lateinit var traktResult: List<Int>
    private lateinit var tmdbResult: Array<TMDBMovie?>

    private var resultLiveData: MutableLiveData<List<TMDBMovie>> = MutableLiveData()

    fun data(): LiveData<List<TMDBMovie>> = resultLiveData

    private var traktObserver = object : DisposableObserver<List<Any>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Any>) {
            traktResult = mapResultToIMDBIds(t)
            tmdbResult = arrayOfNulls(traktResult.size)
            Observable.fromIterable(mapResultToIMDBIds(t).map { movieRepository.getMovie(it) })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(TraktToTMDBParserObserver())
        }

        override fun onError(e: Throwable) {
            Log.e("TRAKT ERROR", e.message)
        }
    }

    abstract fun mapResultToIMDBIds(result: List<Any>) : List<Int>

    private inner class TraktToTMDBParserObserver : DisposableObserver<Observable<TMDBMovie>>() {
        override fun onComplete() {}

        override fun onNext(t: Observable<TMDBMovie>) {
            t.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(TMDBObserver())
        }

        override fun onError(e: Throwable) {
            Log.e("TMDB ERROR", e.message)
        }

    }

    private inner class TMDBObserver : DisposableObserver<TMDBMovie>() {
        override fun onComplete() {}

        override fun onNext(t: TMDBMovie) {
            tmdbResult[traktResult.indexOf(t.id)] = t
            resultLiveData.postValue(tmdbResult.toList().filterNotNull())
        }

        override fun onError(e: Throwable) {
            Log.e("TMDB ERROR", e.message)
        }
    }

    /**
     * Call this function in the Implementations init
     */
    fun observeTraktList(traktResultObservable: Observable<List<Any>>) {
        traktResultObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(600, TimeUnit.MILLISECONDS)
                .subscribe(traktObserver)
    }

}