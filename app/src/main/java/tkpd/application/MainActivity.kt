package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movieapp.Navigator
import com.movieapp.authentication.LoginScreen
import com.tkpd.movieapp.R
import com.tkpd.moviedetail.MovieDetailDirections
import com.tkpd.movielist.MovieListDirections
import dagger.hilt.android.AndroidEntryPoint
import tkpd.application.util.LocalSnackbarHostState
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
            var count by remember {
                mutableStateOf(1)
            }

            navigator.setController(navController)
            CompositionLocalProvider(
                LocalSnackbarHostState provides scaffoldState.snackbarHostState
            ) {
                Scaffold(scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavBar(navController, count)
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = MovieListDirections.destination
                    ) {
                        //movie list screen
                        MovieListDirections.screenWithPaddingBottomBar(
                            this,
                            innerPadding.calculateBottomPadding()
                        ) {
                            count++
                        }

                        //movie detail screen
                        MovieDetailDirections.screen(this)

                        //login screen
                        composable("authentication") {
                            LoginScreen(){
                                count++
                            }
                        }
                    }
                }
            }

        }
    }
}