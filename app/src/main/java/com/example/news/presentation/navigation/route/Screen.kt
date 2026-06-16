package com.example.news.presentation.navigation.route

sealed class Screen(val route: String) {
    //Root Graph
    object MainHomeScreen : Screen(route = "main_screen")
    object Authentication: Screen(route = "auth_screen")
    
    
    // Auth Graph
    object GetStart : Screen(route = "get_screen")
    object LoginScreen : Screen(route = "login_screen")
    object SignUpScreen : Screen(route = "signup_screen")
    
    //Home Graph
    object HomeScreen : Screen(route = "home_screen")
    object DetailScreen : Screen(route = "detail_screen")
    
    object BookMarkScreen: Screen(route = "bookmark_screen")
    object SearchScreen : Screen(route = "search_screen")
}


