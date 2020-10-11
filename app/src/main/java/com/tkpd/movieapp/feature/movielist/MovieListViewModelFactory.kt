package com.tkpd.movieapp.feature.movielist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.datasource.movielist.MovieListCacheDataStoreImpl
import com.tkpd.movieapp.datasource.movielist.MovieListRemoteDataSourceImpl
import com.tkpd.movieapp.datasource.movielist.MovieListRepository
import com.tkpd.movieapp.feature.movielist.view.MovieListViewModel

/**
 * Created by Yehezkiel on 10/05/20
 */
class MovieListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(
            MovieListRepository(
                MovieListRemoteDataSourceImpl(),
                MovieListCacheDataStoreImpl(context)
            )
        ) as T
    }
}