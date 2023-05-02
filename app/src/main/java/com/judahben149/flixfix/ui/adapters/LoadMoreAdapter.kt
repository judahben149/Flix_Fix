package com.judahben149.flixfix.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.flixfix.databinding.ItemLoadMoreBinding

class LoadMoreAdapter(private val retry: () -> Unit): LoadStateAdapter<LoadMoreAdapter.LoadMoreViewHolder>() {

    inner class LoadMoreViewHolder(private val binding: ItemLoadMoreBinding, retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun setData(state: LoadState) {
            binding.prgBarLoadMore.isVisible = state is LoadState.Loading
            binding.tvLoadMore.isVisible = state is LoadState.Error
            binding.btnRetryLoadMore.isVisible = state is LoadState.Error
        }

        init {
            binding.btnRetryLoadMore.setOnClickListener {
                retry()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreViewHolder {
        val binding = ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadMoreViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadMoreViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }
}