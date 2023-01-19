package com.movieapp

import androidx.navigation.NamedNavArgument

object MovieDetailDirections {
    val root = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "movie_detail"

    }
}