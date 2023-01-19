package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
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

            Scaffold(bottomBar = {
                BottomNavBar(navigator.navController)
            }) { innerPadding ->
                NavHost(
                    navController = navigator.navController,
                    startDestination = MovieListDirections.root.destination,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    MovieListDirections.root.screen(this)
                    MovieDetailDirections.root.screen(this)
                }
            }
        }
    }
}