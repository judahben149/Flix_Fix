package com.judahben149.flixfix.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.judahben149.flixfix.data.local.MovieDao
import com.judahben149.flixfix.data.local.MovieDatabase
import com.judahben149.flixfix.data.local.entity.MovieEntityRemoteKey
import com.judahben149.flixfix.data.local.entity.MovieListEntity
import com.judahben149.flixfix.data.remote.MoviesService
import com.judahben149.flixfix.domain.mappers.MovieMapper
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class DiscoverMoviesRemoteMediator(
    private val database: MovieDatabase,
    private val moviesService: MoviesService,
    private val initialPage: Int = 1
) : RemoteMediator<Int, MovieListEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieListEntity>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey = getLastKey(state)
                        ?: throw InvalidObjectException("Last key is null - Your fxn")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)

                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.REFRESH -> {
                    val remoteKey = getClosestKey(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            val response = moviesService.fetchDiscoverMoviesList(page)
            val endOfPagination = response.body()?.data?.size!! < state.config.pageSize

            if (response.isSuccessful) {
                response.body()?.let { movieDto ->

                    if (loadType == LoadType.REFRESH) {
                        database.movieDao.deleteAllArticles()
                        database.remoteKeyDao.deleteAllRemoteKeys()
                    }

                    val prevKey = if (page == initialPage) null else page.minus(1)
                    val nextKey = if (endOfPagination) null else page.plus(1)

                    val remoteKeyList = response.body()?.data?.map { moviesDto ->
                        MovieEntityRemoteKey(moviesDto.id, prevKey, nextKey)
                    }
                    val movieEntityList = movieDto.data.map {
                        MovieMapper.toMovieListDBEntity(it)
                    }

                    remoteKeyList?.let { keysList ->
                        database.remoteKeyDao.insertAllRemoteKeys(keysList)
                    }

                    database.movieDao.insertMovies(movieEntityList)

                }
                MediatorResult.Success(endOfPagination)
            }
            else {
                MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }

        return MediatorResult.Success(endOfPaginationReached = true)
    }

    private suspend fun getClosestKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.let { movieEntity ->
                database.remoteKeyDao.getAllRemoteKeys(movieEntity.id)
            }
        }
    }

    private suspend fun getLastKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.lastItemOrNull()?.let { movieEntity ->
            database.remoteKeyDao.getAllRemoteKeys(movieEntity.id)
        }
    }
}