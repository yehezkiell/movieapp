package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movieapp.ApplinkConst
import com.movieapp.Navigator
import com.movieapp.authentication.LoginScreen
import com.movieapp.generateDestinationArguments
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
                    startDestination = MovieListDirections.destination,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    //movie list screen
                    MovieListDirections.screenWithPaddingBottomBar(
                        this,
                        0.dp
                    )

                    //movie detail screen
                    MovieDetailDirections.screen(this)

                    //login screen
                    composable("authentication") {
                        LoginScreen()
                    }
                }
            }
        }
    }
}