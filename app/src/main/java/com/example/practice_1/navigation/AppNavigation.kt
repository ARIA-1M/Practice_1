package com.example.practice_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practice_1.screens.DetailScreen
import com.example.practice_1.screens.FormScreen
import com.example.practice_1.screens.GalleryScreen
import com.example.practice_1.screens.HomeScreen

import com.example.practice_1.screens.PersonalCardScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = Screen.Form.route){

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Form.route) {
            FormScreen(
                onSave = { ownerName, email, petName, breed, years, description ->
                    navController.navigate(
                        "${Screen.PersonalCard.route}/$ownerName/$email/$petName/$breed/$years/$description"
                    ) {
                        popUpTo(Screen.Form.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                onBack = { navController.navigateUp() }
            )
        }

        composable("${Screen.PersonalCard.route}/{ownerName}/{email}/{petName}/{breed}/{years}/{description}")
        { backStackEntry ->

            PersonalCardScreen(
                ownerName = backStackEntry.arguments?.getString("ownerName") ?: "",
                email = backStackEntry.arguments?.getString("email") ?: "",
                petName = backStackEntry.arguments?.getString("petName") ?: "",
                breed = backStackEntry.arguments?.getString("breed") ?: "",
                years = backStackEntry.arguments?.getString("years") ?: "",
                description = backStackEntry.arguments?.getString("description") ?: "",
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Detail.route) {
            DetailScreen(
                onBack = { navController.navigateUp() }
            )
        }
    }

}





