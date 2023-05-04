package com.judahben149.flixfix.domain.mappers

import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.api.response.MovieDto
import com.judahben149.flixfix.domain.entity.Movie
import com.judahben149.flixfix.domain.entity.MovieDetailedEntity

interface Mapper {

    fun toMovieListModel(moviesDataDto: DiscoverMoviesDataDto): Movie

    fun toMovieDetailedModel(movieDto: MovieDto): MovieDetailedEntity
}