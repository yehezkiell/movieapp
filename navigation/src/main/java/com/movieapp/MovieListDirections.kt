package com.movieapp

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object MovieListDirections {

    val root = object : NavigationCommand {

        override val arguments = listOf(navArgument("movieId") { type = NavType.StringType })

        override val destination = "movieList/{movieId}"

    }

    val Default = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""

    }
}