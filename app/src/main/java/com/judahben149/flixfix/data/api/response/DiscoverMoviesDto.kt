package com.judahben149.flixfix.data.api.response

import com.squareup.moshi.Json

data class DiscoverMoviesDto(
    val page: Int,
    @Json(name = "results")
    val `data`: List<DiscoverMoviesDataDto>,
    val total_pages: Int,
    val total_results: Int
)