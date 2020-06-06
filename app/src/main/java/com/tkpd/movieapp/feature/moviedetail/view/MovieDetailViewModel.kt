package com.tkpd.movieapp.feature.moviedetail.view

import androidx.lifecycle.LiveData
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

    private val _movieDetail = MutableLiveData<Result<MovieDetail?>>()
    val movieDetail: LiveData<Result<MovieDetail?>>
        get() = _movieDetail

    fun getMovieList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieDetailRepository.getMovieDetailFromAPI(movieId)
                _movieDetail.postValue(data)
            } catch (e: Throwable) {
                _movieDetail.postValue(Result.Error(e))
            }
        }
    }
}