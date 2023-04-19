package com.judahben149.flixfix.domain.mappers

import com.judahben149.flixfix.data.api.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.domain.entity.Movie

object MovieMapper {

    fun buildFrom(discoverMoviesResponse: DiscoverMoviesDataDto): Movie {
        return Movie(
            id = discoverMoviesResponse.id,
            backdropPath = discoverMoviesResponse.backdrop_path,
            title = discoverMoviesResponse.title,
            releaseDate = discoverMoviesResponse.release_date
        )
    }
}