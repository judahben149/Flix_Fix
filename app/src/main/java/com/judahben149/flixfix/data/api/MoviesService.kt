package com.judahben149.flixfix.data.api

import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import com.judahben149.flixfix.data.api.response.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMoviesList(@Query("page") pageNumber: Int): Response<DiscoverMoviesDto>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") id: Int) : Response<MovieDto>
}