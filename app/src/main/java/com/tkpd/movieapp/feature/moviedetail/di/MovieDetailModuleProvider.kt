package com.tkpd.movieapp.feature.moviedetail.di

import androidx.lifecycle.ViewModel
import com.tkpd.movieapp.di.ViewModelKey
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailViewModel
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
abstract class MovieDetailModuleProvider {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieDetailViewModel): ViewModel
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieDetailModule {
    // this for better separation, and to add specify dependencies at MovieList
}
