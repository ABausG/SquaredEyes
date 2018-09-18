package es.antonborri.squaredeyes.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.antonborri.squaredeyes.R
import es.antonborri.squaredeyes.data.repository.ShowRepository
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow
import es.antonborri.squaredeyes.helpers.getTMDBBackdropPath
import es.antonborri.squaredeyes.inflate
import javax.inject.Inject

class BackdropShowAdapter @Inject constructor(): ListAdapter<TMDBShow, BackdropShowAdapter.ShowViewHolder>(ShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder = ShowViewHolder(parent.inflate(R.layout.holder_movie))

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) = holder.bind(getItem(position))


    inner class ShowViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(show: TMDBShow) {
            view.findViewById<TextView>(R.id.movie_title).text = show.name
            Picasso.get().load(getTMDBBackdropPath(show.backdropPath ?: "")).into(view.findViewById<ImageView>(R.id.movie_backdrop))
        }
    }

}

