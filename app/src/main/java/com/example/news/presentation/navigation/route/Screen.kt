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
    object HomeScreen : Screen(route = "profile_screen")
    object DetailScreen : Screen(route = "detail_screen")
}


