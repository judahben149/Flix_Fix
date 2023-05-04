package com.judahben149.flixfix.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.judahben149.flixfix.R
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.databinding.ItemCardMovieBinding
import com.judahben149.flixfix.domain.entity.Movie
import com.judahben149.flixfix.utils.Constants
import com.judahben149.flixfix.utils.Extensions.parseFriendlyDate
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MovieListAdapter(
    private val context: Context,
    private val onMovieItemClicked: (id: Int) -> Unit
) : PagingDataAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MoviesAdapterDiffer()) {

    inner class MovieListViewHolder(val binding: ItemCardMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movieItem: Movie) {
            binding.tvMovieName.text = movieItem.title
            binding.tvMovieDate.text = movieItem.releaseDate.parseFriendlyDate()
            binding.cardItemMovie.setOnClickListener {
                onMovieItemClicked(movieItem.id)
            }

            Glide.with(context)
                .load(Constants.BACKDROP_BASE_URL + movieItem.posterPath)
                .into(binding.ivMovieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ItemCardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }


    class MoviesAdapterDiffer() : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }
}