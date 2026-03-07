package com.example.practice_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practice_1.screens.CatProfileData
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
                onSave = {
                    navController.navigate(Screen.PersonalCard.route) {
                        popUpTo(Screen.Form.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.PersonalCard.route) {
            PersonalCardScreen(
                ownerName = CatProfileData.ownerName,
                email = CatProfileData.email,
                petName = CatProfileData.petName,
                breed = CatProfileData.breed,
                years = CatProfileData.years,
                description = CatProfileData.description,
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Detail.route) {
            DetailScreen()
        }
    }

}





