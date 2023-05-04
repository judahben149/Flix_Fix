package com.judahben149.flixfix.ui.views

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.judahben149.flixfix.R
import com.judahben149.flixfix.databinding.FragmentMovieDetailBinding
import com.judahben149.flixfix.domain.entity.MovieDetailedEntity
import com.judahben149.flixfix.ui.viewmodels.MovieViewModel
import com.judahben149.flixfix.utils.Constants
import com.judahben149.flixfix.utils.Extensions.loadImage
import com.judahben149.flixfix.utils.Extensions.parseFriendlyDate
import com.judahben149.flixfix.utils.Extensions.toRunTimeString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private var movieId: Int = 0

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        val id = arguments?.getInt("MOVIE_ID")
        id?.let { movieId = it }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetail.observe(viewLifecycleOwner) {movieDetails ->
            setViews(movieDetails)
        }

        viewModel.getMovieDetails(movieId)
    }

    private fun setViews(movieDetails: MovieDetailedEntity) {
        binding.tvOverviewMovieDetail.text = movieDetails.overview
        binding.tvMovieTitle.text = movieDetails.title
        binding.tvReleaseDate.text = "Release Date: " + movieDetails.release_date.parseFriendlyDate()
        binding.tvRuntime.text = "Runtime: " + movieDetails.runtime.toRunTimeString()

        binding.ivPosterImage.loadImage(requireContext(), Constants.BACKDROP_BASE_URL + movieDetails.poster_path) { }

        binding.ivBackdropImage.loadImage(requireContext(), Constants.BACKDROP_BASE_URL + movieDetails.backdrop_path) {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}