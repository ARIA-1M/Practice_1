package com.example.practice_1.navigation

sealed class ScreenType {
    object WithoutBottomNav : ScreenType()
    object WithBottomNav : ScreenType()
}

sealed class Screen(val route: String, val screenType: ScreenType) {
    object Home : Screen("home", ScreenType.WithoutBottomNav)
    object Form : Screen("form", ScreenType.WithBottomNav)
    object Gallery : Screen("gallery", ScreenType.WithoutBottomNav)
    object Detail : Screen("detail", ScreenType.WithoutBottomNav)
    object PersonalCard : Screen("personal_card", ScreenType.WithoutBottomNav)
}

sealed class NavItem(val title: String, val route: String) {
    object Home : NavItem("Главная", Screen.Home.route)
    object Form : NavItem("Анкета", Screen.Form.route)
    object Gallery : NavItem("Галерея", Screen.Gallery.route)
    object Detail : NavItem("Список", Screen.Detail.route)
    object PersonalCard : NavItem("Карточка", Screen.PersonalCard.route)
}