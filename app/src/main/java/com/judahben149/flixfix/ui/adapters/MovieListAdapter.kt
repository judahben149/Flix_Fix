package com.judahben149.flixfix.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.flixfix.databinding.ItemCardMovieBinding
import com.judahben149.flixfix.domain.entity.Movie

class MovieListAdapter: ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(
    MovieListDiffUtil()
) {

    class MovieListViewHolder(private val binding: ItemCardMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movieItem: Movie) {
            binding.tvMovieName.text = movieItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = ItemCardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class MovieListDiffUtil: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

    }
}