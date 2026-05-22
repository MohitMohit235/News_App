package com.example.news.presentation.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.authentication.LoginScreen
import com.example.news.presentation.authentication.SinghUpScreen
import com.example.news.presentation.navigation.route.Screen
import com.example.news.presentation.onbording.MainNewsScreen
import com.example.news.presentation.onbording.NewsDetailScreen
import com.example.news.presentation.onbording.OnBordScreen
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    
    val startDestination = if (currentUser != null) {
        Screen.MainHomeScreen.route
    } else {
        Screen.Authentication.route
    }
    
    NavHost(
            navController = navController,
            startDestination = startDestination
    ) {
        navigation(
                startDestination = Screen.GetStart.route,
                route = Screen.Authentication.route
        ) {
            composable(Screen.GetStart.route) {
                OnBordScreen(
                        onNextClick = {
                            navController.navigate(Screen.LoginScreen.route)
                        }
                )
            }
            
            composable(Screen.LoginScreen.route) {
                LoginScreen(
                        onSignup = {
                            navController.navigate(Screen.SignUpScreen.route)
                        },
                        onSuccessLogin = {
                            navController.navigate(Screen.MainHomeScreen.route) {
                                popUpTo(Screen.Authentication.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navController = navController
                )
            }
            
            composable(Screen.SignUpScreen.route) {
                SinghUpScreen(
                        navController = navController,
                        onSuccessSignup = {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.SignUpScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                )
            }
        }
        
        navigation(
                startDestination = Screen.HomeScreen.route,
                route = Screen.MainHomeScreen.route
        ) {
            composable(Screen.HomeScreen.route) {
                MainNewsScreen(
                        OndetailClick = {
                            navController.navigate(Screen.DetailScreen.route)
                        }
                )
            }
            
            composable (Screen.DetailScreen.route){
                NewsDetailScreen(navController)
            }
            
        }
        
    }
}