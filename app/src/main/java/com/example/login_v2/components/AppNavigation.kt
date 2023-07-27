package com.example.login_v2.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_v2.componentes_crud.ProductosModel
import com.example.login_v2.pages.home.AddData
import com.example.login_v2.pages.home.GetData
import com.example.login_v2.pages.home.HomePage
import com.example.login_v2.pages.home.SplashScreen
import com.example.login_v2.pages.login.LoginScreen

@Composable
fun AppNavigation(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = navigations.SplashScreen.route){
        composable(navigations.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(navigations.MainScreen.route){
            TiendaApp()
        }
    }
}