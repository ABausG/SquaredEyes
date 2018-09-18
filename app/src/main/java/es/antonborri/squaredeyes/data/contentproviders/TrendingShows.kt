package es.antonborri.squaredeyes.data.contentproviders

import es.antonborri.squaredeyes.data.model.trakt.TrendingTraktShow
import es.antonborri.squaredeyes.data.repository.ShowRepository
import es.antonborri.squaredeyes.network.TraktApi
import io.reactivex.Observable
import javax.inject.Inject

class TrendingShows @Inject constructor(traktApi: TraktApi, showRepository: ShowRepository) : ShowContent(traktApi, showRepository) {
    override fun mapTraktResultToIds(traktResult: List<Any>): List<Int> {
        val trueType : List<TrendingTraktShow> = traktResult as List<TrendingTraktShow>
        return trueType.map { it.show.ids.tmdb }
    }

    init {
        super.observeTraktList(traktApi.getTrendingShows() as Observable<List<Any>>)
    }

}