package es.antonborri.squaredeyes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.antonborri.squaredeyes.data.contentproviders.PopularMovies
import es.antonborri.squaredeyes.data.contentproviders.TrendingShows
import es.antonborri.squaredeyes.data.model.trakt.TraktMovie
import es.antonborri.squaredeyes.data.repository.MovieRepository
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(val popularMovies: PopularMovies, val trendingShows: TrendingShows) : ViewModel() {


}