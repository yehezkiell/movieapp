package com.tkpd.moviedetail

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movieapp.ApplinkConst
import com.movieapp.NavigationCommand
import com.movieapp.getMainPath

object MovieDetailDirections : NavigationCommand {
    const val PARAM_MOVIE_ID = "movieId"

    override val arguments = emptyList<NamedNavArgument>()

    override val mainPath = ApplinkConst.MOVIE_DETAIL_PATH.getMainPath()

    override val destination = ApplinkConst.MOVIE_DETAIL_PATH

    override fun screen(builder: NavGraphBuilder) {
        builder.composable(
            destination,
            arguments = listOf(navArgument(PARAM_MOVIE_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            MovieDetailUI()
        }
    }
}