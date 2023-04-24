package com.judahben149.flixfix.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.judahben149.flixfix.databinding.FragmentHomeBinding
import com.judahben149.flixfix.domain.mappers.MovieMapper
import com.judahben149.flixfix.ui.adapters.MovieListAdapter
import com.judahben149.flixfix.ui.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieListAdapter

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

        adapter = MovieListAdapter(requireContext()) {movieId ->
            Snackbar.make(binding.root, movieId, Snackbar.LENGTH_SHORT).show()
        }

        recyclerView = binding.rvMovieList
        recyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer { movieResponse ->
            if (movieResponse != null) {
                val movieList = movieResponse.data.map { MovieMapper.buildFrom(it) }

                adapter.submitList(movieList)
            } else {
                Snackbar.make(binding.root, "An error occured...", Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.fetchMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}