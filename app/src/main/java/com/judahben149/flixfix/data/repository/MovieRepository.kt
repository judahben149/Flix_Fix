package com.judahben149.flixfix.data.repository

import androidx.paging.PagingData
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.api.response.MovieDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesDataDto>>

    suspend fun getMovieDetails(id: Int): Response<MovieDto>

}