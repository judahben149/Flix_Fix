package com.judahben149.flixfix.domain.entity

data class MovieListModel(
    val id: Int = 0,
    val backdropPath: String = "",
    val title: String = "",
    val releaseDate: String = "",
    val posterPath: String = ""
//    val genreIds: List<Int> = emptyList()
)
