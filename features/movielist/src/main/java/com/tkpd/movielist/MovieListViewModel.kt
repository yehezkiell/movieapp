package com.tkpd.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.ApplinkConst
import com.movieapp.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tkpd.abstraction.extension.Result.Error
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.data.PopularMovies
import com.tkpd.movielist.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yehezkiel on 10/05/20
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieListRepository: MovieListRepository,
                                             @Singleton private val navigator: Navigator) :
        ViewModel() {

    private val _topRatedMovies = MutableLiveData<Result<PopularMovies?>>()
    val topRatedMovies: LiveData<Result<PopularMovies?>>
        get() = _topRatedMovies

    init {
        _topRatedMovies.value = Result.Loading
        getMovieList()
    }

    fun onMovieClicked(movieId: String) {
        navigator.route(ApplinkConst.MOVIE_DETAIL_PATH, movieId, builder = {
            //noop
        })
    }

    private fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieListRepository.getMovieListFromAPI()
                _topRatedMovies.postValue(data)
            } catch (e: Throwable) {
                _topRatedMovies.postValue(Error(e))
            }
        }
    }
}