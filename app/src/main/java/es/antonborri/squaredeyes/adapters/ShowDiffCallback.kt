package es.antonborri.squaredeyes.adapters

import androidx.recyclerview.widget.DiffUtil
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow

class ShowDiffCallback : DiffUtil.ItemCallback<TMDBShow>() {
    override fun areItemsTheSame(oldItem: TMDBShow, newItem: TMDBShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TMDBShow, newItem: TMDBShow): Boolean {
       return oldItem == newItem
    }


}