package com.judahben149.flixfix.data.repository

import android.util.Log
import com.judahben149.flixfix.data.api.ApiClient
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiClient: ApiClient): MovieRepository {

    override suspend fun fetchMovieList(): DiscoverMoviesDto? {
        Log.d("jj","Repository - fetching fxn called")

        val discoverMoviesRequest = apiClient.fetchDiscoverMoviesList()

        if (discoverMoviesRequest.isSuccessful) {
            return discoverMoviesRequest.body()!!
        } else return null
    }
}