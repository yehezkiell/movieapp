package com.tkpd.movieapp.feature.moviedetail.di

import androidx.lifecycle.ViewModel
import com.tkpd.movieapp.di.ViewModelKey
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailFragment
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailViewModel
import com.tkpd.movieapp.feature.movielist.view.MovieListFragment
import com.tkpd.movieapp.feature.movielist.view.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Yehezkiel on 30/05/20
 */
@Module
abstract class MovieDetailModuleProvider {

    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    internal abstract fun movieDetailFragment(): MovieDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieDetailViewModel): ViewModel
}

@Module
abstract class MovieDetailModule {
    // this for better separation, and to add specify dependencies at MovieList
}
