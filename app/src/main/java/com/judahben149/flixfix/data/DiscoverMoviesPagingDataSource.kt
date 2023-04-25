package com.judahben149.flixfix.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto

//class DiscoverMoviesPagingDataSource(private val movieService: MoviesService): PagingSource<Int, DiscoverMoviesDto>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMoviesDto> {
//        val pageNumber = params.key ?: 1
//        return try {
//            val response = movieService.fetchDiscoverMoviesList()
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, DiscoverMoviesDto>): Int? {
//
//    }
//}