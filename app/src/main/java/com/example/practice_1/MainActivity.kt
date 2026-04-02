package com.example.practice_1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.practice_1.data.database.AppDatabase
import com.example.practice_1.navigation.AppNavigation
import com.example.practice_1.navigation.MainAppScaffold
import com.example.practice_1.repository.CatRepository
import com.example.practice_1.repository.FactRepository
import com.example.practice_1.repository.UserRepository
import com.example.practice_1.screens.DetailScreen

import com.example.practice_1.ui.theme.Practice_1Theme
import com.example.practice_1.viewmodel.CatViewModel
import com.example.practice_1.viewmodel.FactViewModel
import com.example.practice_1.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = AppDatabase.getDatabase(this)

        val factRepository = FactRepository(database.factDao())
        val userRepository = UserRepository(database.userDao())
        val catRepository = CatRepository(database.catDao())

        val factViewModel = FactViewModel(factRepository)
        val userViewModel = UserViewModel(userRepository)
        val catViewModel = CatViewModel(catRepository)

        setContent {
            Practice_1Theme {
                val navController = rememberNavController()
                MainAppScaffold(
                    navController = navController,
                    factViewModel = factViewModel,
                    userViewModel = userViewModel,
                    catViewModel = catViewModel
                )
            }

        }
    }
}


