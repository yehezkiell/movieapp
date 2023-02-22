package com.tkpd.movielist

import androidx.compose.ui.unit.Dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieapp.ApplinkConst
import com.movieapp.NavigationCommand
import com.movieapp.getMainPath

object MovieListDirections : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val mainPath = ApplinkConst.MOVIE_LIST_PATH.getMainPath()

    override val destination = ApplinkConst.MOVIE_LIST_PATH

    override fun screen(builder: NavGraphBuilder) {
    }

    fun screenWithPaddingBottomBar(builder: NavGraphBuilder, dp: Dp, click: () -> Unit) {
        builder.composable(destination) {
            MovieListMainView(bottomBarHeight = dp) {
                click.invoke()
            }
        }
    }
}