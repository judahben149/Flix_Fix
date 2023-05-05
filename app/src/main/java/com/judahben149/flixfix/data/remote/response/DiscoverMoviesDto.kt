package com.judahben149.flixfix.data.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesDto(
    val page: Int,
    @SerializedName("results")
    val `data`: List<DiscoverMoviesDataDto>,
    val total_pages: Int,
    val total_results: Int
)