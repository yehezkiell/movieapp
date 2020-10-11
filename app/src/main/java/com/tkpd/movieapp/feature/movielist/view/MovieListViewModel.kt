package com.tkpd.movieapp.feature.movielist.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tkpd.movieapp.datasource.movielist.MovieListRepository
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 10/05/20
 */
class MovieListViewModel(movieListRepository: MovieListRepository) : ViewModel() {

    private val _topRatedMovies = movieListRepository.getPopularMovieList()
    val topRatedMovies: LiveData<Result<PopularMovies?>>
        get() = _topRatedMovies
}