package com.tkpd.movieapp.movielist.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.TopRatedMovies
import com.tkpd.movieapp.movielist.MovieListRepository
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tkpd.movieapp.util.Result.Success
import com.tkpd.movieapp.util.Result.Error
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 10/05/20
 */
class MovieListViewModel(private val movieListRepository: MovieListRepository) : ViewModel() {

    val topRatedMovies = MutableLiveData<Result<TopRatedMovies?>>()

    fun getMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieListRepository.getMovieListFromAPI()
                topRatedMovies.postValue(data)
            } catch (e: Throwable) {
                topRatedMovies.postValue(Error(e))
            }
        }
    }

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

            } catch (e: Throwable) {

            }
        }
    }


    private suspend fun getMovieDetailById(): MovieDetail? {
        return RetrofitInstanceBuilder.RETROFIT_INSTANCE.getMovieDetail(618344).body()
    }
}