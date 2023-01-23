package com.tkpd.moviedetail.model

import com.tkpd.abstraction.data.MovieDetail

data class MovieDetailState(
    val detail: MovieDetail = MovieDetail(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
