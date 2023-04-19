package com.judahben149.flixfix.data.api

import android.util.Log
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(private val moviesService: MoviesService) {

    suspend fun fetchDiscoverMoviesList(): Response<DiscoverMoviesDto> {
        Log.d("jj","ApiClient - fetching fxn called")

        val result = moviesService.fetchDiscoverMoviesList()
        Log.d("jj","ApiClient - ${result.body()?.data?.get(0)?.overview}")

        return result
    }
}