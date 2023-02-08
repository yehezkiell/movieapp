package com.tkpd.movielist.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MovieListState(
    val movieList: PopularMovies = PopularMovies(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val bottomPadding: Dp = 0.dp
)
