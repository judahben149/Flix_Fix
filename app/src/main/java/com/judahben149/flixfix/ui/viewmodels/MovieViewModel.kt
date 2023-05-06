package com.judahben149.flixfix.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.judahben149.flixfix.data.repository.MovieRepositoryImpl
import com.judahben149.flixfix.domain.entity.MovieModel
import com.judahben149.flixfix.domain.mappers.MovieMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl): ViewModel() {

    val _movieDetail = MutableLiveData<MovieModel>()
    val movieDetail: LiveData<MovieModel> get() = _movieDetail

    private val _movieList = repository.fetchDiscoverMovieList().map { pagingData ->
        pagingData.map { dtoResponse ->
            MovieMapper.dtoToMovieListModel(dtoResponse)
        }
    }.cachedIn(viewModelScope)

    val movieList get() = _movieList

    private val _movieListCached = repository.fetchDiscoverMovieListCached().map { pagingData ->
        pagingData.map {  movieEntity ->
            MovieMapper.entityToMovieListModel(movieEntity)
        }
    }.cachedIn(viewModelScope)

    val movieListCached get() = _movieListCached

    fun getMovieDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = repository.getMovieDetails(id).body()
            movie?.let { _movieDetail.postValue(MovieMapper.toMovieModel(it)) }
        }
    }

}