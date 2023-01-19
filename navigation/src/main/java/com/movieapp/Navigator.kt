package com.movieapp

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

class Navigator {
    lateinit var navController: NavHostController

    fun setController(controller: NavHostController) {
        navController = controller
    }

    fun route(path: String,
              vararg arguments: String,
              builder: (NavOptionsBuilder) -> Unit) {
        val mainPath = generateDestinationArguments(path, *arguments)

        navController.navigate(mainPath) {
            builder.invoke(this)
        }
    }
}