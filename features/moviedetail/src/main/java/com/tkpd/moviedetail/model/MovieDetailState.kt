package com.tkpd.moviedetail.model

data class MovieDetailState(
    val detail: MovieDetail = MovieDetail(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
