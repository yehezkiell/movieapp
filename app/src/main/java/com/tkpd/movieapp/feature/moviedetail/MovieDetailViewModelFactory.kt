package com.tkpd.movieapp.feature.moviedetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.data.movielist.MovieCacheDataStoreImpl
import com.tkpd.movieapp.data.movielist.MovieDetailRemoteDataStoreImpl
import com.tkpd.movieapp.data.repository.MovieDetailRepository
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailViewModel

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(
            MovieDetailRepository(MovieDetailRemoteDataStoreImpl(),
            MovieCacheDataStoreImpl(context))
        ) as T
    }
}