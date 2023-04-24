package com.judahben149.flixfix.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.flixfix.data.api.response.DiscoverMoviesDto
import com.judahben149.flixfix.data.repository.MovieRepository
import com.judahben149.flixfix.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl): ViewModel() {

    private val _movies: MutableLiveData<DiscoverMoviesDto?> = MutableLiveData()
    val movies: LiveData<DiscoverMoviesDto?> get() = _movies


    fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("jj","Viewmodel - fetching fxn called")

            val response = repository.fetchMovieList()
            _movies.postValue(response)

            withContext(Dispatchers.Main) {
                Timber.tag("Minee").d(response?.data?.get(2)?.title)
                Log.d("gygy", "fetchMovieList: ${response?.data?.get(2)?.title}")
            }

        }
    }
}