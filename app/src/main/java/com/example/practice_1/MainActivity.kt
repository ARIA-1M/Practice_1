package com.example.practice_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.practice_1.navigation.AppNavigation
import com.example.practice_1.screens.DetailScreen

import com.example.practice_1.ui.theme.Practice_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice_1Theme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }

        }
    }
}
