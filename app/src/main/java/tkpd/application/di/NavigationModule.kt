package tkpd.application.di

import com.movieapp.NavigationManager
import com.movieapp.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {
    @Singleton
    @Provides
    fun providesNavigator() = Navigator()
}