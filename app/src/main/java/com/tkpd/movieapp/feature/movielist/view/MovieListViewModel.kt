package com.tkpd.movieapp.feature.movielist.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.datasource.repository.MovieListRepository
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.datasource.repository.MovieListRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tkpd.movieapp.util.Result.Error
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 10/05/20
 */
class MovieListViewModel(private val movieListRepository: MovieListRepository) : ViewModel() {

    val topRatedMovies = MutableLiveData<Result<PopularMovies?>>()

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieListRepository.getMovieListFromAPI()
                topRatedMovies.postValue(data)
            } catch (e: Throwable) {
                topRatedMovies.postValue(Error(e))
            }
        }
    }
}