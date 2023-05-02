package com.judahben149.flixfix.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.judahben149.flixfix.databinding.FragmentHomeBinding
import com.judahben149.flixfix.databinding.ItemLoadMoreBinding
import com.judahben149.flixfix.ui.adapters.LoadMoreAdapter
import com.judahben149.flixfix.ui.adapters.MovieListAdapter
import com.judahben149.flixfix.ui.viewmodels.MovieViewModel
import com.judahben149.flixfix.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    private var adapter: MovieListAdapter? = null

    val navController by lazy {
        findNavController()
    }
    val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieList.collectLatest { movies ->
                    adapter?.submitData(movies)
                }
            }
        }


//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            adapter?.loadStateFlow?.collect {
//                val state = it.refresh
//                footerBinding?.prgBarLoadMore?.isVisible = state is LoadState.Loading
//            }
//        }
    }

    private fun initViews() {
        adapter = MovieListAdapter(requireContext()) { movieId ->
            Snackbar.make(binding.root, movieId, Snackbar.LENGTH_SHORT).show()
        }

        recyclerView = binding.rvMovieList
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }
}