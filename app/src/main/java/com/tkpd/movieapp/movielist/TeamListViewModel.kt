package com.tkpd.movieapp.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.TopRatedMovies
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

/**
 * Created by Yehezkiel on 10/05/20
 */
class TeamListViewModel : ViewModel() {

    val topRatedMovies = MutableLiveData<TopRatedMovies>()
    val movieDetail = MutableLiveData<MovieDetail>()

    fun getNBATeams() {
        viewModelScope.launch(Dispatchers.IO) {
            topRatedMovies.postValue(getNbaAPI())
        }
    }

    fun getMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetail.postValue(getMovieDetailById())
        }
    }

    private suspend fun getNbaAPI(): TopRatedMovies? {
        return RetrofitInstanceBuilder.RETROFIT_INSTANCE.getTopRatedMovies(1).body()
    }

    private suspend fun getMovieDetailById(): MovieDetail? {
        return RetrofitInstanceBuilder.RETROFIT_INSTANCE.getMovieDetail(618344).body()
    }
}