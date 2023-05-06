package com.judahben149.flixfix.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.judahben149.flixfix.data.local.MovieDao
import com.judahben149.flixfix.data.local.entity.MovieListEntity
import com.judahben149.flixfix.data.paging.DiscoverMoviesPagingDataSource
import com.judahben149.flixfix.data.paging.DiscoverMoviesRemoteMediator
import com.judahben149.flixfix.data.remote.MoviesService
import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.remote.response.MovieDto
import com.judahben149.flixfix.utils.Constants.NETWORK_PAGE_SIZE
import com.judahben149.flixfix.utils.Constants.STARTING_PAGE_INDEX
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val movieDao: MovieDao
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

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchDiscoverMovieListCached(): Flow<PagingData<MovieListEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 5
            ),
            remoteMediator = DiscoverMoviesRemoteMediator(movieDao, moviesService, STARTING_PAGE_INDEX),
            pagingSourceFactory = { movieDao.getAllMovies() }
        ).flow
    }

    @WorkerThread
    override suspend fun getMovieDetails(id: Int): Response<MovieDto> {
        return moviesService.fetchMovieDetails(id)
    }
}