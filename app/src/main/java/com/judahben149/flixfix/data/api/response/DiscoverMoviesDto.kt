package com.judahben149.flixfix.data.api.response

data class DiscoverMoviesDto(
    val page: Int,
    val `data`: List<DiscoverMoviesDataDto>,
    val total_pages: Int,
    val total_results: Int
)