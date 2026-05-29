package com.example.news.presentation.navigation.navGraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.authentication.LoginScreen
import com.example.news.presentation.authentication.SinghUpScreen
import com.example.news.presentation.components.MianSearchScreen
import com.example.news.presentation.navigation.route.Screen
import com.example.news.presentation.onbodingscreens.MainNewsScreen
import com.example.news.presentation.onbodingscreens.NewsDetailScreen
import com.example.news.presentation.onbodingscreens.OnBordScreen
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
            composable(
                    Screen.GetStart.route,
                    enterTransition = {
                        slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(400)
                        )
                    }
            ) {
                OnBordScreen(
                        onNextClick = {
                            navController.navigate(Screen.LoginScreen.route)
                        }
                )
            }
            
            composable(
                    Screen.LoginScreen.route,
                    enterTransition = {
                        slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(200)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(200)
                        )
                    }
            ) {
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
            
            composable(
                    Screen.SignUpScreen.route,
                    enterTransition = {
                        slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(200)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(200)
                        )
                    }
            ) {
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
            composable(
                    Screen.HomeScreen.route,
                    enterTransition = {
                        slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(400)
                        )
                    }
            ) {
                MainNewsScreen(
                        navController = navController,
                        OndetailClick = {
                            navController.navigate(Screen.DetailScreen.route)
                        }
                )
            }
            
            composable(
                    Screen.DetailScreen.route,
                    enterTransition = {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(400))
                    },
                    exitTransition = {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(400))
                    }
            ) {
                NewsDetailScreen(navController)
            }
            
            
            composable(
                    Screen.SearchScreen.route,
                    enterTransition = {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(400))
                    },
                    exitTransition = {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(400))
                    }
            ) {
                MianSearchScreen(navController = navController)
            }
            
        }
        
    }
}