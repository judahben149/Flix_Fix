package com.judahben149.flixfix.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.flixfix.R
import com.judahben149.flixfix.databinding.FragmentMovieListBinding
import com.judahben149.flixfix.databinding.ItemLoadMoreBinding
import com.judahben149.flixfix.ui.adapters.LoadMoreAdapter
import com.judahben149.flixfix.ui.adapters.MovieListAdapter
import com.judahben149.flixfix.ui.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    val binding get() = _binding!!

    private lateinit var footerBinding: ItemLoadMoreBinding

    lateinit var adapter: MovieListAdapter
    lateinit var recyclerView: RecyclerView

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        footerBinding = ItemLoadMoreBinding.inflate(inflater, container, false)
        initViews()
        collectMovieList()

        return binding.root
    }

    private fun initViews() {
        adapter = MovieListAdapter(requireContext()) { id ->
//            Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putInt("MOVIE_ID", id)
            findNavController().navigate(R.id.MovieDetailFragment, bundle)
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView = binding.rvDiscoverMovies
        recyclerView.adapter = adapter.withLoadStateFooter(
            LoadMoreAdapter {
                adapter.retry()
            }
        )
        recyclerView.layoutManager = layoutManager
    }

    private fun collectMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieList.collect {
                    adapter.submitData(pagingData = it)
                }
            }
        }
    }

    private fun handleLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter?.loadStateFlow?.collect {
                    val state = it.refresh
                    footerBinding?.prgBarLoadMore?.isVisible = state is LoadState.Loading
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}