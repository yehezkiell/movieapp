package com.tkpd.movieapp.feature.movielist.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by Yehezkiel on 30/05/20
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieListModule {
  // this for better separation, and to add specify dependencies at MovieList
}