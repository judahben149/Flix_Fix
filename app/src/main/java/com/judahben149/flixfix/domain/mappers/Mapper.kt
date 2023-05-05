package com.judahben149.flixfix.domain.mappers

import com.judahben149.flixfix.data.local.entity.MovieEntity
import com.judahben149.flixfix.data.local.entity.MovieListEntity
import com.judahben149.flixfix.data.remote.response.DiscoverMoviesDataDto
import com.judahben149.flixfix.data.remote.response.MovieDto
import com.judahben149.flixfix.domain.entity.MovieListModel
import com.judahben149.flixfix.domain.entity.MovieModel

interface Mapper {

    //to remove
    fun dtoToMovieListModel(moviesDataDto: DiscoverMoviesDataDto): MovieListModel

    fun entityToMovieListModel(movieListEntity: MovieListEntity): MovieListModel

    fun toMovieListDBEntity(moviesDataDto: DiscoverMoviesDataDto): MovieListEntity

    //to remove
    fun toMovieModel(movieDto: MovieDto): MovieModel

    fun toMovieDBEntity(movieDto: MovieDto): MovieEntity
}