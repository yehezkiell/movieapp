package com.tkpd.moviedetail.di

import com.tkpd.moviedetail.view.MovieDetailFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = [MovieDetailBindModule::class])
interface MovieDetailComponent {
    // Factory that is used to create instances of this subcomponent
    // so that ApplicationComponent knows how to create instances of MovieListComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieDetailComponent
    }

    fun inject(movieDetailFragment: MovieDetailFragment)
}