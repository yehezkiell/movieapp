package com.tkpd.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movielist.repository.MovieListRepositoryImpl
import com.tkpd.movielist.view.MovieListViewModel

/**
 * Created by Yehezkiel on 10/05/20
 */
class MovieListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(MovieListRepositoryImpl()) as T
    }
}