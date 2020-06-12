package com.tkpd.movieapp.feature.movielist.di

import androidx.lifecycle.ViewModel
import com.tkpd.movieapp.di.ViewModelKey
import com.tkpd.movieapp.feature.movielist.view.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

/**
 * Created by Yehezkiel on 30/05/20
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieListModuleProvider {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieListViewModel): ViewModel
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieListModule {
  // this for better separation, and to add specify dependencies at MovieList
}