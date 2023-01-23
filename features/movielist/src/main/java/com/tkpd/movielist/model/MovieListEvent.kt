package com.tkpd.movielist.model

import com.tkpd.abstraction.data.PopularMovies

sealed class MovieListEvent {
    data class MovieList(val movieList: PopularMovies) : MovieListEvent()
    data class Error(val t: Throwable) : MovieListEvent()
    object Loading : MovieListEvent()
}
