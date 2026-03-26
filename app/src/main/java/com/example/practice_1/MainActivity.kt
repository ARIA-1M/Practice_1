package com.example.practice_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.practice_1.data.database.AppDatabase
import com.example.practice_1.navigation.AppNavigation
import com.example.practice_1.navigation.MainAppScaffold
import com.example.practice_1.repository.FactRepository
import com.example.practice_1.screens.DetailScreen

import com.example.practice_1.ui.theme.Practice_1Theme
import com.example.practice_1.viewmodel.FactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = AppDatabase.getDatabase(this)
        val repository = FactRepository(database.factDao())
        val factViewModel = FactViewModel(repository)
        setContent {
            Practice_1Theme {
                val navController = rememberNavController()
                MainAppScaffold(
                    navController = navController,
                    factViewModel = factViewModel
                )
            }

        }
    }
}
