package es.antonborri.squaredeyes.data.contentproviders

import es.antonborri.squaredeyes.data.repository.MovieRepository
import es.antonborri.squaredeyes.network.TraktApi
import javax.inject.Inject

class PopularMovies  @Inject constructor(traktApi: TraktApi, movieRepository: MovieRepository) : MovieContent(traktApi, movieRepository){

    init {
        super.observeTraktList(traktApi.getPopularMovies())
    }

}