package com.example.practice_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practice_1.screens.FormScreen
import com.example.practice_1.screens.GalleryScreen

import com.example.practice_1.screens.PersonalCardScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = Screen.Home.route){
        /*сomposable(Screen.Home.route) {
            HomeScreen(
                onNavigateToForm = { navController.navigate(Screen.Form.route) },
                onNavigateToGallery = { navController.navigate(Screen.Gallery.route) },
                onNavigateToPersonalCard = { navController.navigate(Screen.PersonalCard.route) },
                onNavigateToList = { navController.navigate(Screen.List.route) },
                onNavigateToDetail = { navController.navigate(Screen.Detail.route) }
            )
        }

        composable(Screen.Form.route) {
            FormScreen(
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.PersonalCard.route) {
            PersonalCardScreen(
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.List.route) {
            ListScreen(
                onItemClick = { /* пока ничего */ },
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Detail.route) {
            DetailScreen(
                onBack = { navController.navigateUp() }
            )
        }*/
    }

}





