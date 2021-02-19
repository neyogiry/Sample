package com.neyogiry.android.sample.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.domain.model.Movie
import com.neyogiry.android.sample.util.ImageLoaderHelper

class MoviesAdapter(private val imageLoader: ImageLoaderHelper)
    : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(itemView: View, private val imageLoader: ImageLoaderHelper)
        : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val loading: View = itemView.findViewById(R.id.loading)
        private var movie: Movie? = null

        fun bind(movie: Movie?) {
            this.movie = movie
            title.text = movie?.title ?: ""
            description.text = movie?.description ?: ""
            imageLoader.load(thumbnail, movie?.imagePath ?: "", loading)
        }

        companion object {
            fun create(parent: ViewGroup, imageLoader: ImageLoaderHelper): MovieViewHolder {
                return MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false), imageLoader
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