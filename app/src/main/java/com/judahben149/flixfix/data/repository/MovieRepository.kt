package com.judahben149.flixfix.data.repository

import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto

interface MovieRepository {

    suspend fun fetchMovieList(): DiscoverMoviesDto?

}