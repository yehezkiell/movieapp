package com.tkpd.moviedetail.di

import com.tkpd.moviedetail.repository.MovieDetailRepository
import com.tkpd.moviedetail.repository.MovieDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDetailModule {
    @Binds
    abstract fun bindRepository(repo: MovieDetailRepositoryImpl): MovieDetailRepository
}