package com.tkpd.movieapp.feature.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.datasource.repository.MovieDetailRepositoryImpl
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailViewModel

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(MovieDetailRepositoryImpl()) as T
    }
}