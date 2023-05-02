package com.judahben149.flixfix.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import okio.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1
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





































//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMoviesDataDto> {
//        val pageNumber = params.key ?: 1
//
//        return try {
//            Log.d(Constants.TAG, "Paging Data Source Load fxn called")
//
//            val response = movieService.fetchDiscoverMoviesList(pageNumber)
//            val pagedResponse = response.body()
//            val data = pagedResponse?.data
//
//            var nextPageNumber: Int? = null
//            if (pagedResponse?.page != pagedResponse?.total_pages) {
//                nextPageNumber = pagedResponse?.page?.plus(1)
//            }
//
//            LoadResult.Page(
//                data = data.orEmpty(),
//                prevKey = null,
//                nextKey = nextPageNumber
//            )
//        } catch (ex: Exception) {
//            LoadResult.Error(ex)
//        }
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, DiscoverMoviesDataDto>): Int? {
//        Log.d(Constants.TAG, "Paging Data Source Refresh keys fxn called")
//
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}