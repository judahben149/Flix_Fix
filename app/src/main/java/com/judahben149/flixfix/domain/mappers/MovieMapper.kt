package com.judahben149.flixfix.domain.mappers

import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.remote.response.MovieDto
import com.judahben149.flixfix.domain.entity.Movie
import com.judahben149.flixfix.domain.entity.MovieDetailedEntity

object MovieMapper: Mapper {

    override fun toMovieListModel(discoverMoviesResponse: DiscoverMoviesDataDto): Movie {
        return Movie(
            id = discoverMoviesResponse.id,
            backdropPath = discoverMoviesResponse.backdrop_path,
            title = discoverMoviesResponse.title,
            releaseDate = discoverMoviesResponse.release_date,
            posterPath = discoverMoviesResponse.poster_path
        )
    }

    override fun toMovieDetailedModel(movieDto: MovieDto): MovieDetailedEntity {
        return MovieDetailedEntity(
            adult = movieDto.adult,
            backdrop_path = movieDto.backdrop_path ?: "",
            budget = movieDto.budget.toDouble(),
            genres = movieDto.genres,
            homepageUrl = movieDto.homepage ?: "",
            id = movieDto.id,
            imdb_id = movieDto.imdb_id ?: "",
            original_language = movieDto.original_language,
            original_title = movieDto.original_title,
            overview = movieDto.overview ?: "",
            popularity = movieDto.popularity,
            poster_path = movieDto.poster_path ?: "",
            release_date = movieDto.release_date,
            revenue = movieDto.revenue.toDouble(),
            runtime = movieDto.runtime ?: 0,
            spoken_languages = movieDto.spoken_languages,
            status = movieDto.status,
            tagline = movieDto.tagline ?: "",
            title = movieDto.title,
            video = movieDto.video,
            vote_average = movieDto.vote_average,
            vote_count = movieDto.vote_count
        )
    }
}