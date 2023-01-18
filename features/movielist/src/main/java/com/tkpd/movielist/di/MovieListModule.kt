package com.tkpd.movielist.di

import com.tkpd.movielist.repository.MovieListRepository
import com.tkpd.movielist.repository.MovieListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieListModule {
    @Binds
    abstract fun bindRepository(repo: MovieListRepositoryImpl): MovieListRepository
}