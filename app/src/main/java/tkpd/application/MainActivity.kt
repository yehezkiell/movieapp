package tkpd.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movieapp.Navigator
import com.movieapp.authentication.AccountScreen
import com.movieapp.favorite.ui.FavoriteUI
import com.tkpd.abstraction.ui.ActivitySharedViewModel
import com.tkpd.abstraction.util.ComposeUtil.rememberViewModelStoreOwner
import com.tkpd.movieapp.R
import com.tkpd.moviedetail.MovieDetailDirections
import com.tkpd.movielist.MovieListDirections
import dagger.hilt.android.AndroidEntryPoint
import tkpd.application.util.LocalNavGraphViewModelStoreOwner
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

            /**
             * Provide viewModel store owner activity, act like shared viewModel
             * https://stackoverflow.com/a/70413449/8868638
             */
            val vmStoreOwner = rememberViewModelStoreOwner()

            navigator.setController(navController)
            CompositionLocalProvider(
                LocalSnackbarHostState provides scaffoldState.snackbarHostState,
                LocalNavGraphViewModelStoreOwner provides vmStoreOwner
            ) {
                val sharedViewModel: ActivitySharedViewModel =
                    viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
                val isLoggedIn = sharedViewModel.isLoggedIn.collectAsState()

                var startDestination by remember { mutableStateOf(MovieListDirections.destination) }
                var prevLoginState by remember { mutableStateOf(isLoggedIn.value) }

                SideEffect {
                    prevLoginState = isLoggedIn.value
                }

                if (prevLoginState != isLoggedIn.value) {
                    startDestination = "authentication"
                }

                Scaffold(scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavBar(navController)
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        //movie list screen
                        MovieListDirections.screenWithPaddingBottomBar(
                            this,
                            innerPadding.calculateBottomPadding()
                        )

                        //movie detail screen
                        MovieDetailDirections.screen(this)

                        if (isLoggedIn.value) {
                            composable("favorite") {
                                FavoriteUI()
                            }
                        }

                        //login screen
                        composable("authentication") {
                            AccountScreen()
                        }

                    }
                }
            }
        }
    }
}