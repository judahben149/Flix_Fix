package com.judahben149.flixfix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.judahben149.flixfix.data.DiscoverMoviesPagingDataSource
import com.judahben149.flixfix.data.api.ApiClient
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.utils.Constants.NETWORK_PAGE_SIZE
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MovieRepository {

    override fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesDataDto>> {

        return Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 5
            )
        ) {
            DiscoverMoviesPagingDataSource(moviesService)
        }.flow
    }
}