package com.judahben149.flixfix.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.judahben149.flixfix.data.remote.MoviesService
import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.utils.Constants.STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException

class DiscoverMoviesPagingDataSource(private val service: MoviesService): PagingSource<Int, DiscoverMoviesDataDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMoviesDataDto> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = service.fetchDiscoverMoviesList(currentPage)
            val movies = response.body()?.data!!

            val movieResponse = mutableListOf<DiscoverMoviesDataDto>()
            movieResponse.addAll(movies)

            Log.d("MyPagingSource", "Loaded ${movies.size} items")

            LoadResult.Page(
                data = movieResponse,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(1),
                nextKey = currentPage.plus(1)
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DiscoverMoviesDataDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}