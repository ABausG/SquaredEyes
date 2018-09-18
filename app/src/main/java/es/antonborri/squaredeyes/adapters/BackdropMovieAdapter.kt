package es.antonborri.squaredeyes.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.antonborri.squaredeyes.R
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.helpers.getTMDBBackdropPath
import es.antonborri.squaredeyes.inflate
import javax.inject.Inject

class BackdropMovieAdapter @Inject constructor() : ListAdapter<TMDBMovie, BackdropMovieAdapter.MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(parent.inflate(R.layout.holder_movie))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(getItem(position))


    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: TMDBMovie) {
            view.findViewById<TextView>(R.id.movie_title).text = movie.title
            Picasso.get().load(getTMDBBackdropPath(movie.backdropPath
                    ?: "")).into(view.findViewById<ImageView>(R.id.movie_backdrop))
        }
    }

}

