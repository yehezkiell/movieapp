package com.tkpd.movieapp.feature.moviedetail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.tkpd.movieapp.datasource.repository.MovieDetailRepository
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result
import kotlinx.coroutines.Dispatchers

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModel @AssistedInject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    @Assisted val movieId: Int) : ViewModel() {

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            movieId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(movieId) as T
            }
        }
    }

    val movieDetail: LiveData<Result<MovieDetail>?> = liveData(Dispatchers.IO) {
        try {
            emit(movieDetailRepository.getMovieDetailFromAPI(movieId))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(movieId: Int): MovieDetailViewModel
    }
}