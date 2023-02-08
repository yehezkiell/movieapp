package tkpd.application

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, val resourceId: String, val icon: ImageVector) {
    object Home : Screen("movieList", "Home", Icons.Filled.Home)
    object Account : Screen("authentication", "Account", Icons.Filled.AccountBox)
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (items.any { it.route == currentDestination?.route }) {
        BottomNavigation(
            backgroundColor = Color.Gray, contentColor = Color.Black
        ) {
            items.forEach { item ->
                BottomNavigationItem(icon = {
                    Icon(
                        item.icon,
                        contentDescription = null
                    )
                },
                    label = { Text(item.resourceId) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    inclusive = true
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }
}

@Composable
@Preview
fun BottomNavigationPreview() {
    BottomNavBar(rememberNavController())
}