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
    object CatAdd : Screen("cat_add", ScreenType.WithBottomNav)
    object CatEdit : Screen("cat_edit/{petId}", ScreenType.WithBottomNav)
}

sealed class NavItem(val title: String, val route: String) {
    object Home : NavItem("Главная", Screen.Home.route)
    object Form : NavItem("Анкета", Screen.Form.route)
    object Gallery : NavItem("Галерея", Screen.Gallery.route)
    object Detail : NavItem("Список", Screen.Detail.route)
    object PersonalCard : NavItem("Карточка", Screen.PersonalCard.route)
    object CatAdd : NavItem("Добавление котов", Screen.CatAdd.route)
    object CatEdit : NavItem("Добавление котов", Screen.CatEdit.route)
}