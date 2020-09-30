package com.tkpd.moviedetail.di

import androidx.lifecycle.ViewModel
import com.tkpd.abstraction.di.ViewModelKey
import com.tkpd.moviedetail.repository.MovieDetailRepository
import com.tkpd.moviedetail.repository.MovieDetailRepositoryImpl
import com.tkpd.moviedetail.view.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailBindModule{
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindViewModel(viewmodel: MovieDetailViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repo: MovieDetailRepositoryImpl): MovieDetailRepository
}