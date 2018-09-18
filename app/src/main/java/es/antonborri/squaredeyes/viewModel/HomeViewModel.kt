package es.antonborri.squaredeyes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.antonborri.squaredeyes.data.model.PopularMovie
import es.antonborri.squaredeyes.data.model.repository.MovieRepository
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(val traktApi: TraktApi, val movieRepository: MovieRepository) : ViewModel() {

    lateinit var traktPopularMovies: List<Int>
    lateinit var tmdbPopularMovies: Array<TMDBMovie?>

    private var popularMovies: MutableLiveData<List<TMDBMovie>> = MutableLiveData()


    fun popularMovies(): LiveData<List<TMDBMovie>> = popularMovies

    private var disposableObserver = object : DisposableObserver<List<PopularMovie>>() {
        override fun onComplete() {}

        override fun onNext(value: List<PopularMovie>) {
            traktPopularMovies = value.map { it -> it.ids.tmdb }
            tmdbPopularMovies = arrayOfNulls<TMDBMovie>(traktPopularMovies.size)
            val list = value.map { movieRepository.getMovie(it.ids.tmdb) }
            Observable.fromIterable(list)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(TraktToTMDBParserObserver())
        }

        override fun onError(e: Throwable) {
            Log.e("TRAKT ERROR", e.message)
        }
    }

    private inner class TraktToTMDBParserObserver : DisposableObserver<Observable<TMDBMovie>>() {
        override fun onComplete() {
            //popularMovies.postValue(tmdbPopularMovies.toList().filterNotNull())
        }

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
        override fun onComplete() {
            Log.i("FINISHED", "Finished one movie")
        }

        override fun onNext(t: TMDBMovie) {
            tmdbPopularMovies[traktPopularMovies.indexOf(t.id)] = t
            popularMovies.postValue(tmdbPopularMovies.toList().filterNotNull())
        }

        override fun onError(e: Throwable) {
            Log.e("TMDB ERROR", e.message)
        }

    }

    init {
        traktApi.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(600, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }
}