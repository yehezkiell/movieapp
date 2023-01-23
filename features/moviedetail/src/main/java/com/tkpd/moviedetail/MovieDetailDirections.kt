package com.tkpd.moviedetail

import androidx.navigation.*
import androidx.navigation.compose.composable
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
            MovieDetailUI(backStackEntry.arguments?.getInt(PARAM_MOVIE_ID, 0) ?: 0)
        }
    }
}