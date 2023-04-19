package com.judahben149.flixfix.data.api

import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import retrofit2.Response
import retrofit2.http.GET


interface MoviesService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMoviesList(): Response<DiscoverMoviesDto>
}