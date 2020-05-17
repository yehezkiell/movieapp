package com.tkpd.movieapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.movielist.MovieListRepository
import com.tkpd.movieapp.movielist.view.MovieListViewModel

/**
 * Created by Yehezkiel on 10/05/20
 */
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(MovieListRepository()) as T
    }
}