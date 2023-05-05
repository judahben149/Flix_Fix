package com.judahben149.flixfix.domain.entity

import com.judahben149.flixfix.data.remote.response.GenreX
import com.judahben149.flixfix.data.remote.response.SpokenLanguage

data class MovieDetailedEntity(

    val adult: Boolean = false,
    val backdrop_path: String = "",
    val budget: Double = 0.0,
    val genres: List<GenreX>,
    val homepageUrl: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val revenue: Double = 0.0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage>,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)