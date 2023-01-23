package com.tkpd.moviedetail.model

import com.tkpd.abstraction.data.MovieDetail

sealed class MovieDetailEvent {
    data class Detail(val detail: MovieDetail) : MovieDetailEvent()
    data class Error(val t: Throwable) : MovieDetailEvent()
    object Loading : MovieDetailEvent()
}