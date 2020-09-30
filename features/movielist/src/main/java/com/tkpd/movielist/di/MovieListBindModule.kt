package com.tkpd.movielist.di

import androidx.lifecycle.ViewModel
import com.tkpd.abstraction.di.ViewModelKey
import com.tkpd.movielist.repository.MovieListRepository
import com.tkpd.movielist.repository.MovieListRepositoryImpl
import com.tkpd.movielist.view.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieListBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindViewModel(viewmodel: MovieListViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repo: MovieListRepositoryImpl): MovieListRepository
}