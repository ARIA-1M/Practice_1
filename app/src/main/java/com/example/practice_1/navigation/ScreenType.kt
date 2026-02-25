package com.example.practice_1.navigation

sealed class ScreenType {
    object WithoutBottomNav : ScreenType()
    object WithBottomNav : ScreenType()
}

sealed class Screen(val route: String, val screenType: ScreenType) {
    object Home : Screen("home", ScreenType.WithBottomNav)
    object Form : Screen("form", ScreenType.WithoutBottomNav)
    object Gallery : Screen("gallery", ScreenType.WithoutBottomNav)
    object List : Screen("list", ScreenType.WithoutBottomNav)
    object Detail : Screen("detail", ScreenType.WithoutBottomNav)
    object PersonalCard : Screen("personal_card", ScreenType.WithoutBottomNav)
    object Content : Screen("content", ScreenType.WithoutBottomNav)
}

sealed class NavItem(val title: String, val route: String) {
    object Home : NavItem("Главная", Screen.Home.route)
    object Form : NavItem("Анкета", Screen.Form.route)
    object Gallery : NavItem("Галерея", Screen.Gallery.route)
    object List : NavItem("Список", Screen.List.route)
    object PersonalCard : NavItem("Карточка", Screen.PersonalCard.route)
}