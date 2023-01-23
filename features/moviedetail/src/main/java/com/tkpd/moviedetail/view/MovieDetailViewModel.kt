package com.tkpd.moviedetail.view

import androidx.lifecycle.*
import com.tkpd.abstraction.data.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tkpd.abstraction.extension.Result
import com.tkpd.moviedetail.MovieDetailDirections
import com.tkpd.moviedetail.model.MovieDetailEvent
import com.tkpd.moviedetail.model.MovieDetailState
import com.tkpd.moviedetail.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Yehezkiel on 29/05/20
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val argument =
        checkNotNull(savedStateHandle.get<Int>(MovieDetailDirections.PARAM_MOVIE_ID))

    private val _detailData = MutableStateFlow(MovieDetailState())
    val detailData: StateFlow<MovieDetailState>
        get() = _detailData

    init {
        getMovieList(argument)
    }

    private fun getMovieList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieDetailRepository.getMovieDetailFromAPI(movieId)
                sentEvent(MovieDetailEvent.Loading)

                sentEvent(MovieDetailEvent.Detail((data as Result.Success).data))
            } catch (e: Throwable) {
                sentEvent(MovieDetailEvent.Error(e))
            }
        }
    }

    private fun sentEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.Loading -> {
                _detailData.update {
                    it.copy(isLoading = true, isError = false)
                }
            }
            is MovieDetailEvent.Error -> {
                _detailData.update {
                    it.copy(isLoading = false, isError = true)
                }
            }
            is MovieDetailEvent.Detail -> {
                _detailData.update {
                    it.copy(isLoading = false, isError = false, detail = event.detail)
                }
            }
        }
    }
}