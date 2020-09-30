package com.tkpd.movielist.di

interface MovieListProvider {
    fun provideMovieListComponent(): MovieListComponent
}