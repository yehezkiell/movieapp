package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movieapp.MovieDetailDirections
import com.movieapp.MovieListDirections
import com.movieapp.NavigationManager
import com.movieapp.Navigator
import com.tkpd.movieapp.R
import com.tkpd.moviedetail.MovieDetailUI
import com.tkpd.movielist.MovieListMainView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            val navController = rememberNavController()
            navigator.setController(navController)

            NavHost(
                navController = navigator.navController,
                startDestination = MovieListDirections.root.destination
            ) {
                composable(MovieListDirections.root.destination) {
                    MovieListMainView()
                }
                composable(MovieDetailDirections.root.destination) {
                    MovieDetailUI()
                }
            }
        }
    }
}