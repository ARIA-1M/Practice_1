package com.example.practice_1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = "home"){

    }

}





