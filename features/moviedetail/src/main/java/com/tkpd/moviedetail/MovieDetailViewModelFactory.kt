package com.tkpd.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.moviedetail.repository.MovieDetailRepositoryImpl
import com.tkpd.moviedetail.view.MovieDetailViewModel

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(MovieDetailRepositoryImpl()) as T
    }
}