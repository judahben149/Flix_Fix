package com.judahben149.flixfix.data.repository

import androidx.paging.PagingData
import com.judahben149.flixfix.data.local.entity.MovieListEntity
import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.remote.response.MovieDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesDataDto>>

    fun fetchDiscoverMovieListCached(): Flow<PagingData<MovieListEntity>>

    suspend fun getMovieDetails(id: Int): Response<MovieDto>

}