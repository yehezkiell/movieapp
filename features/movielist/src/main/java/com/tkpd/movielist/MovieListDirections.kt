package com.tkpd.movielist

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.movieapp.ApplinkConst
import com.movieapp.NavigationCommand
import com.movieapp.getMainPath

object MovieListDirections {

    val root = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val mainPath = ApplinkConst.MOVIE_LIST_PATH.getMainPath()

        override val destination = ApplinkConst.MOVIE_LIST_PATH

        override fun screen(builder: NavGraphBuilder) {
            builder.composable(destination) {
                MovieListMainView()
            }
        }
    }
}