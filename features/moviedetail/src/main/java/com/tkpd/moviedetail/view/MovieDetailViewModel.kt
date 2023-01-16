package com.tkpd.moviedetail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.abstraction.data.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tkpd.abstraction.extension.Result
import com.tkpd.moviedetail.repository.MovieDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail?>()
    val movieDetail: LiveData<MovieDetail?>
        get() = _movieDetail

    private val _showError = MutableStateFlow(false)
    val showError: StateFlow<Boolean>
        get() = _showError

    private val _showLoading = MutableStateFlow(true)
    val showLoading: StateFlow<Boolean>
        get() = _showLoading

    fun getMovieList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieDetailRepository.getMovieDetailFromAPI(movieId)
                _showError.emit(false)
                _showLoading.emit(false)

                _movieDetail.postValue((data as Result.Success).data)
            } catch (e: Throwable) {
                _showError.emit(true)
                _showLoading.emit(false)
            }
        }
    }
}