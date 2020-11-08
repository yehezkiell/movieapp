package com.tkpd.movieapp.feature.moviedetail.view

import androidx.lifecycle.*
import com.tkpd.movieapp.data.repository.MovieDetailRepository
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()

    val movieDetail: LiveData<Result<MovieDetail?>> = movieId.switchMap {
        liveData {
            emit(movieDetailRepository.getMovieDetail(it))
        }.switchMap {
            it
        }
    }

    fun getMovieDetail(id: Int) {
        movieId.value = id
    }
}