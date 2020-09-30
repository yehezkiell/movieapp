package tkpd.application

import android.app.Application
import android.content.Context
import com.tkpd.moviedetail.di.MovieDetailComponent
import com.tkpd.moviedetail.di.MovieDetailProvider
import com.tkpd.movielist.di.MovieListComponent
import com.tkpd.movielist.di.MovieListProvider
import tkpd.application.di.ApplicationComponent
import tkpd.application.di.DaggerApplicationComponent


class MyApplication : Application(), MovieListProvider, MovieDetailProvider {

    //Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    fun initializeComponent(): ApplicationComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerApplicationComponent.factory().create(applicationContext)
    }


    override fun provideMovieListComponent(): MovieListComponent {
        return appComponent.movieListComponent().create()
    }

    override fun provideMovieDetailComponent(): MovieDetailComponent {
        return appComponent.movieDetailComponent().create()
    }
}