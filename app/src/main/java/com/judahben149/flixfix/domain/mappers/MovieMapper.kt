package com.judahben149.flixfix.domain.mappers

import com.judahben149.flixfix.data.local.entity.MovieEntity
import com.judahben149.flixfix.data.local.entity.MovieListEntity
import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.remote.response.MovieDto
import com.judahben149.flixfix.domain.entity.MovieListModel
import com.judahben149.flixfix.domain.entity.MovieModel

object MovieMapper : Mapper {

    //to remove
    override fun dtoToMovieListModel(discoverMoviesResponse: DiscoverMoviesDataDto): MovieListModel {
        return MovieListModel(
            id = discoverMoviesResponse.id,
            backdropPath = discoverMoviesResponse.backdrop_path,
            title = discoverMoviesResponse.title,
            releaseDate = discoverMoviesResponse.release_date,
            posterPath = discoverMoviesResponse.poster_path
        )
    }

    override fun entityToMovieListModel(movieListEntity: MovieListEntity): MovieListModel {
        return MovieListModel(
            id = movieListEntity.id,
            backdropPath = movieListEntity.backdrop_path,
            title = movieListEntity.title,
            releaseDate = movieListEntity.release_date,
            posterPath = movieListEntity.poster_path
        )
    }

    override fun toMovieListDBEntity(moviesDataDto: DiscoverMoviesDataDto): MovieListEntity {
        return MovieListEntity(
            adult = moviesDataDto.adult,
            backdrop_path = moviesDataDto.backdrop_path,
            id = moviesDataDto.id,
            original_language = moviesDataDto.original_language,
            original_title = moviesDataDto.original_title,
            overview = moviesDataDto.overview,
            popularity = moviesDataDto.popularity,
            poster_path = moviesDataDto.poster_path,
            release_date = moviesDataDto.release_date,
            title = moviesDataDto.title,
            video = moviesDataDto.video,
            vote_average = moviesDataDto.vote_average,
            vote_count = moviesDataDto.vote_count
        )
    }

    //to remove
    override fun toMovieModel(movieDto: MovieDto): MovieModel {
        return MovieModel(
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

    override fun toMovieDBEntity(movieDto: MovieDto): MovieEntity {
        return MovieEntity(
            id = movieDto.id,
            adult = movieDto.adult,
            backdrop_path = movieDto.backdrop_path ?: "",
            budget = movieDto.budget.toDouble(),
            homepageUrl = movieDto.homepage ?: "",
            imdb_id = movieDto.imdb_id ?: "",
            original_language = movieDto.original_language,
            original_title = movieDto.original_title,
            overview = movieDto.overview ?: "",
            popularity = movieDto.popularity,
            poster_path = movieDto.poster_path ?: "",
            release_date = movieDto.release_date,
            revenue = movieDto.revenue.toDouble(),
            runtime = movieDto.runtime ?: 0,
            status = movieDto.status,
            tagline = movieDto.tagline ?: "",
            title = movieDto.title,
            video = movieDto.video,
            vote_average = movieDto.vote_average,
            vote_count = movieDto.vote_count
        )
    }
}