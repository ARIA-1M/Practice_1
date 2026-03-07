package com.example.practice_1.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainAppScaffold(navController: NavHostController) {
    // Получаем текущий скрин
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Задаем где показываем не показываем нижнюю панель
    val shouldShowBottomNav = when {
        currentRoute == Screen.Form.route -> false
        else -> true
    }

    // Состояние для выбранного пункта в меню
    var selectedItem by remember { mutableStateOf<NavItem>(NavItem.Home) }

    // Синхронизируем выбранный пункт с текущим экраном
    LaunchedEffect(currentRoute) {
        selectedItem = when {
            currentRoute == Screen.Home.route -> NavItem.Home
            currentRoute == Screen.Form.route -> NavItem.Form
            currentRoute == Screen.Gallery.route -> NavItem.Gallery
            currentRoute == Screen.Detail.route -> NavItem.Detail
            currentRoute == Screen.PersonalCard.route -> NavItem.PersonalCard
            else -> selectedItem
        }
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNav) {
                BottomNavigationPanel(
                    selectedItem = selectedItem,
                    onItemSelected = { item ->
                        selectedItem = item

                        // Переходим на соответствующий экран
                        when (item) {
                            NavItem.Home -> {
                                navController.navigate(Screen.Home.route) {
                                    // Очищаем стек чтобы не было дубликатов
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            }
                            NavItem.Form -> {
                                navController.navigate(Screen.Form.route) {
                                    popUpTo(Screen.Form.route) { inclusive = true }
                                }
                            }
                            NavItem.Gallery -> {
                                navController.navigate(Screen.Gallery.route) {
                                    popUpTo(Screen.Gallery.route) { inclusive = true }
                                }
                            }
                            NavItem.Detail -> {
                                navController.navigate(Screen.Detail.route) {
                                    popUpTo(Screen.Detail.route) { inclusive = true }
                                }
                            }
                            NavItem.PersonalCard -> {
                                navController.navigate(Screen.PersonalCard.route) {
                                    popUpTo(Screen.PersonalCard.route) { inclusive = true }
                                }
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            AppNavigation(navController)
        }
    }
}