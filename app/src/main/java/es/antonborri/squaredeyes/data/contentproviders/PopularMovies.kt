package es.antonborri.squaredeyes.data.contentproviders

import es.antonborri.squaredeyes.data.model.trakt.TraktMovie
import es.antonborri.squaredeyes.data.repository.MovieRepository
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import javax.inject.Inject

class PopularMovies  @Inject constructor(traktApi: TraktApi, movieRepository: MovieRepository) : MovieContent(traktApi, movieRepository){

    override fun mapResultToIMDBIds(result: List<Any>): List<Int> {
        val trueType: List<TraktMovie> = result as List<TraktMovie>
        return trueType.map { it.ids.tmdb }
    }

    init {
        super.observeTraktList(traktApi.getPopularMovies() as Observable<List<Any>>)
    }

}