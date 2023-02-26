package tkpd.application

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tkpd.abstraction.ui.ActivitySharedViewModel
import tkpd.application.util.LocalNavGraphViewModelStoreOwner

sealed class Screen(val route: String, val textString: String, val icon: ImageVector) {
    object Home : Screen("movieList", "Home", Icons.Filled.Home)
    object Account : Screen("authentication", "Account", Icons.Filled.AccountBox)
    object Favorite : Screen("favorite", "Favorite", Icons.Filled.Favorite)
}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val sharedViewModel: ActivitySharedViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    val isLoggedIn = sharedViewModel.isLoggedIn.collectAsState()

    val items = mutableListOf(
        Screen.Home,
        Screen.Account
    )

    if (isLoggedIn.value) {
        items.add(1, Screen.Favorite)
    }

    if (items.any { it.route == currentDestination?.route }) {
        BottomNavigation(
            backgroundColor = Color.Gray, contentColor = Color.Black
        ) {
            items.forEach {
                NavigationItem(
                    rowScope = this,
                    icon = it.icon,
                    textString = it.textString,
                    route = it.route,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    rowScope: RowScope,
    icon: ImageVector,
    textString: String,
    route: String,
    currentDestination: NavDestination?,
    navController: NavController
) {
    with(rowScope) {
        BottomNavigationItem(icon = {
            Icon(
                icon,
                contentDescription = null
            )
        },
            label = { Text(textString) },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentDestination?.hierarchy?.any { it.route == route } == true,
            onClick = {
                navController.navigate(route) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    }
}

@Composable
@Preview
fun BottomNavigationPreview() {
    BottomNavBar(rememberNavController())
}