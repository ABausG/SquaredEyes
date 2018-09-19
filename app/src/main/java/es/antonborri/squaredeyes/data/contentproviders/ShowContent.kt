package es.antonborri.squaredeyes.data.contentproviders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow
import es.antonborri.squaredeyes.data.repository.ShowRepository
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class ShowContent(private val traktApi: TraktApi, private val showRepository: ShowRepository) {

    private lateinit var traktResult: List<Int>
    private lateinit var tmdbResult: Array<TMDBShow?>

    private var resultLiveData: MutableLiveData<List<TMDBShow>> = MutableLiveData()

    fun data(): LiveData<List<TMDBShow>> = resultLiveData

    private var traktObserver = object : DisposableObserver<List<Any>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Any>) {
            traktResult = mapResultToIMDBIds(t)
            tmdbResult = arrayOfNulls(traktResult.size)
            Observable.fromIterable(mapResultToIMDBIds(t).map { showRepository.getShow(it)})
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(TraktToTMDBParserObserver())
        }

        override fun onError(e: Throwable) {
            Log.e("TRAKT ERROR", e.message)
        }
    }

    abstract fun mapResultToIMDBIds(result: List<Any>) : List<Int>

    private inner class TraktToTMDBParserObserver : DisposableObserver<Observable<TMDBShow>>() {
        override fun onComplete() {}

        override fun onNext(t: Observable<TMDBShow>) {
            t.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(TMDBObserver())
        }

        override fun onError(e: Throwable) {
            Log.e("TMDB ERROR", e.message)
        }

    }

    private inner class TMDBObserver : DisposableObserver<TMDBShow>() {
        override fun onComplete() {}

        override fun onNext(t: TMDBShow) {
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