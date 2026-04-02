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
import com.example.practice_1.viewmodel.CatViewModel
import com.example.practice_1.viewmodel.FactViewModel
import com.example.practice_1.viewmodel.UserViewModel

@Composable
fun AppNavigation(navController: NavHostController,
        factViewModel: FactViewModel,
        userViewModel: UserViewModel,
        catViewModel: CatViewModel
){
    NavHost(navController=navController, startDestination = Screen.Form.route){

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Form.route) {
            FormScreen(
                userViewModel = userViewModel,
                onSave = {
                    navController.navigate(Screen.PersonalCard.route) {
                        popUpTo(Screen.Form.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                catViewModel = catViewModel,
                onBack = { navController.navigateUp() }
            )
        }

        composable(Screen.PersonalCard.route) {
            PersonalCardScreen(
                userViewModel = userViewModel,
                catViewModel = catViewModel,
                onBack = { navController.navigateUp()
                }
            )
        }

        composable(Screen.Detail.route) {
            DetailScreen(viewModel = factViewModel)
        }
    }

}





