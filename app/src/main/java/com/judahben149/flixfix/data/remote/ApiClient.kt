package com.judahben149.flixfix.data.remote

import javax.inject.Inject

class ApiClient @Inject constructor(private val moviesService: MoviesService) {

//    suspend fun fetchDiscoverMoviesList(): Response<DiscoverMoviesDto> {
//        Log.d("jj","ApiClient - fetching fxn called")
//
//        val result = moviesService.fetchDiscoverMoviesList()
//        Log.d("jj","ApiClient - ${result.body()?.data?.get(0)?.overview}")
//
//        return result
//    }
}