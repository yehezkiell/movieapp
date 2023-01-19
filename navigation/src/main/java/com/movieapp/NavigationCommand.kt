package com.movieapp

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val mainPath: String

    val destination: String

    fun screen(builder: NavGraphBuilder)
}