package com.judahben149.flixfix.ui.viewmodels

import com.judahben149.flixfix.domain.entity.Movie
import com.judahben149.flixfix.arch.Event

data class MovieListState(
    val isLoading: Event<Boolean>,
    val isSwipeToRefresh: Event<Boolean>,
    val movieList: List<Movie>
)
