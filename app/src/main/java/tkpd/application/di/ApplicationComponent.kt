package tkpd.application.di

import android.content.Context
import com.tkpd.abstraction.di.ApplicationScope
import com.tkpd.moviedetail.di.MovieDetailComponent
import com.tkpd.movielist.di.MovieListComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, SubComponentModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    // This function exposes the MovieListComponent Factory out of the graph so consumers
    // can use it to obtain new instances of MovieListComponent
    fun movieListComponent(): MovieListComponent.Factory
    fun movieDetailComponent(): MovieDetailComponent.Factory
}