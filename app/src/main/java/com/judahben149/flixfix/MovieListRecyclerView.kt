package com.judahben149.flixfix

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.flixfix.databinding.ItemCardMovieBinding

class MovieListRecyclerView(): ListAdapter<>() {

    class MovieListViewHolder(binding: ItemCardMovieBinding): RecyclerView.ViewHolder(binding.root) {

    }
}