package com.tkpd.movieapp.feature.moviedetail.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.datasource.repository.MovieDetailRepository
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {

    val movieDetail = MutableLiveData<Result<MovieDetail?>>()

    fun getMovieList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieDetailRepository.getMovieDetailFromAPI(movieId)
                movieDetail.postValue(data)
            } catch (e: Throwable) {
                movieDetail.postValue(Result.Error(e))
            }
        }
    }
}