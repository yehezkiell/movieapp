package com.tkpd.movieapp.di

import android.content.Context
import com.tkpd.movieapp.MovieApplication
import com.tkpd.movieapp.feature.moviedetail.di.MovieDetailModuleProvider
import com.tkpd.movieapp.feature.movielist.di.MovieListModuleProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Yehezkiel on 30/05/20
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        MovieListModuleProvider::class,
        MovieDetailModuleProvider::class]
)
interface AppComponent  : AndroidInjector<MovieApplication> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

}