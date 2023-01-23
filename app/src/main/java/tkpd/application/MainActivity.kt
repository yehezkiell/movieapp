package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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
            val scaffoldState = rememberScaffoldState()
            navigator.setController(navController)

            Scaffold(scaffoldState = scaffoldState,
                bottomBar = {
                    BottomNavBar(navController)
                }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = MovieListDirections.destination
                ) {
                    MovieListDirections.screenWithPaddingBottomBar(
                        this,
                        innerPadding.calculateBottomPadding()
                    )
                    MovieDetailDirections.screen(this)
                }
            }
        }
    }
}