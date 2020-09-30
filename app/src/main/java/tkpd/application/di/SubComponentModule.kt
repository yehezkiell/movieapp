package tkpd.application.di

import com.tkpd.movielist.di.MovieListComponent
import dagger.Module

@Module(subcomponents = [MovieListComponent::class])
class SubComponentModule {}