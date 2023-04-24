package com.judahben149.flixfix.data.repository

import android.util.Log
import com.judahben149.flixfix.data.api.ApiClient
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiClient: ApiClient, private val moviesService: MoviesService) {

    suspend fun fetchMovieList(): DiscoverMoviesDto? {
        Log.d("jj","Repository - fetching fxn called")

        val discoverMoviesRequest = moviesService.fetchDiscoverMoviesList("30239e3691bfcb7c062c8383a63cf7a9")

        if (discoverMoviesRequest.isSuccessful) {
            return discoverMoviesRequest.body()!!
        } else return null
    }
}