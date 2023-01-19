package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.movieapp.Navigator
import com.tkpd.movieapp.R
import com.tkpd.moviedetail.MovieDetailDirections
import com.tkpd.movielist.MovieListDirections
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
                MovieListDirections.root.screen(this)
                MovieDetailDirections.root.screen(this)
            }
        }
    }
}