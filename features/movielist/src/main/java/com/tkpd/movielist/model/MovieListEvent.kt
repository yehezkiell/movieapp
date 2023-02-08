package com.tkpd.movielist.model

sealed class MovieListEvent {
    data class MovieList(val movieList: PopularMovies) : MovieListEvent()
    data class Error(val t: Throwable) : MovieListEvent()
    object Loading : MovieListEvent()
}
