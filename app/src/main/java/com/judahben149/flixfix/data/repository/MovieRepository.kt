package com.judahben149.flixfix.data.repository

import androidx.paging.PagingData
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesDataDto>>

}