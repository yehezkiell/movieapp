package com.movieapp

import androidx.navigation.NavHostController

class Navigator {
    lateinit var navController: NavHostController

    fun setController(controller: NavHostController) {
        navController = controller
    }
}