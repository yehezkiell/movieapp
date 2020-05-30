package com.tkpd.movieapp.feature.movielist.di

import androidx.lifecycle.ViewModel
import com.tkpd.movieapp.datasource.repository.MovieListRepository
import com.tkpd.movieapp.di.ViewModelKey
import com.tkpd.movieapp.feature.movielist.view.MovieListFragment
import com.tkpd.movieapp.feature.movielist.view.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by Yehezkiel on 30/05/20
 */

@Module
abstract class MovieListModuleProvider {
    @ContributesAndroidInjector(modules = [MovieListModule::class])
    internal abstract fun movieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieListViewModel): ViewModel
}

@Module
abstract class MovieListModule {
  // this for better separation, and to add specify dependencies at MovieList
}