package com.judahben149.flixfix.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.judahben149.flixfix.databinding.ItemCardMovieBinding
import com.judahben149.flixfix.domain.entity.MovieListModel
import com.judahben149.flixfix.utils.Constants
import com.judahben149.flixfix.utils.Extensions.parseFriendlyDate

class MovieListAdapter(
    private val context: Context,
    private val onMovieItemClicked: (id: Int) -> Unit
) : PagingDataAdapter<MovieListModel, MovieListAdapter.MovieListViewHolder>(MoviesAdapterDiffer()) {

    inner class MovieListViewHolder(val binding: ItemCardMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movieListModelItem: MovieListModel) {
            binding.tvMovieName.text = movieListModelItem.title
            binding.tvMovieDate.text = movieListModelItem.releaseDate.parseFriendlyDate()
            binding.cardItemMovie.setOnClickListener {
                onMovieItemClicked(movieListModelItem.id)
            }

            Glide.with(context)
                .load(Constants.BACKDROP_BASE_URL + movieListModelItem.posterPath)
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


    class MoviesAdapterDiffer() : DiffUtil.ItemCallback<MovieListModel>() {
        override fun areItemsTheSame(
            oldItem: MovieListModel,
            newItem: MovieListModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListModel,
            newItem: MovieListModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}