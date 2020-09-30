package com.tkpd.movielist.di

import com.tkpd.movielist.view.MovieListFragment
import dagger.Subcomponent

@Subcomponent(modules = [MovieListBindModule::class])
interface MovieListComponent {
    // Factory that is used to create instances of this subcomponent
    // so that ApplicationComponent knows how to create instances of MovieListComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieListComponent
    }

    fun inject(movieListFragment: MovieListFragment)
}