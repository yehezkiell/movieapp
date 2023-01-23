package com.tkpd.movielist

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
import com.tkpd.movielist.model.MovieListEvent
import com.tkpd.movielist.model.MovieListState
import com.tkpd.movielist.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yehezkiel on 10/05/20
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieListRepository: MovieListRepository,
                                             @Singleton private val navigator: Navigator) :
        ViewModel() {

    private val _movieList = MutableStateFlow(MovieListState())
    val movieList: StateFlow<MovieListState>
        get() = _movieList

    init {
        getMovieList()
    }

    fun setPadding(dp: Dp) {
        if (dp == 0.dp) return
        _movieList.update {
            it.copy(bottomPadding = dp)
        }
    }

    fun onMovieClicked(movieId: String) {
        navigator.route(ApplinkConst.MOVIE_DETAIL_PATH, movieId, builder = {
            //noop
        })
    }

    private fun setEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.MovieList -> {
                _movieList.update {
                    it.copy(event.movieList, false, false)
                }
            }
            is MovieListEvent.Error -> {
                _movieList.update {
                    it.copy(isLoading = false, isError = true)
                }
            }
            is MovieListEvent.Loading -> {
                _movieList.update {
                    it.copy(isLoading = true, isError = false)
                }
            }
        }
    }

    private fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = movieListRepository.getMovieListFromAPI()

                setEvent(MovieListEvent.MovieList((data as Result.Success).data))
            } catch (e: Throwable) {
                setEvent(MovieListEvent.Error(e))
            }
        }
    }
}