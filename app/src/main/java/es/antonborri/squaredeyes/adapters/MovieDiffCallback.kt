package es.antonborri.squaredeyes.adapters

import androidx.recyclerview.widget.DiffUtil
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie

class MovieDiffCallback : DiffUtil.ItemCallback<TMDBMovie>() {
    override fun areItemsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
       return oldItem == newItem
    }


}