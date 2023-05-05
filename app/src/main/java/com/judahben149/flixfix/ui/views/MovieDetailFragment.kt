package com.judahben149.flixfix.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.judahben149.flixfix.databinding.FragmentMovieDetailBinding
import com.judahben149.flixfix.domain.entity.MovieModel
import com.judahben149.flixfix.ui.viewmodels.MovieViewModel
import com.judahben149.flixfix.utils.Constants
import com.judahben149.flixfix.utils.Extensions.loadImage
import com.judahben149.flixfix.utils.Extensions.parseFriendlyDate
import com.judahben149.flixfix.utils.Extensions.toRunTimeString
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

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

    private fun setViews(movieDetails: MovieModel) {
        binding.tvOverviewMovieDetail.text = movieDetails.overview
        binding.tvMovieTitle.text = movieDetails.title
        binding.tvReleaseDate.text = "Release Date: " + movieDetails.release_date.parseFriendlyDate()
        binding.tvRuntime.text = "Runtime: " + movieDetails.runtime.toRunTimeString()
        binding.tvRating.text = DecimalFormat("#.#").format(movieDetails.vote_average).toString()


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