package com.neyogiry.android.sample.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.domain.model.Movie

class MoviesAdapter
    : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)
        private var movie: Movie? = null

        fun bind(movie: Movie?) {
            this.movie = movie
            title.text = movie?.title ?: ""
            description.text = movie?.description ?: ""
        }

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                )
            }
        }
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

}