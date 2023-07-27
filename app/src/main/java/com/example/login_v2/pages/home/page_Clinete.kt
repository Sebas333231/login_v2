package com.example.login_v2.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.login_v2.components.PagesScreen
import com.example.login_v2.components.navigations
import kotlinx.coroutines.delay

@Composable
fun Home_Page2(
    navController: NavController
){
    Column() {
        Text(text = "Hola Valen")
        Button(
            onClick ={
                navController.popBackStack()
                navController.navigate(PagesScreen.Start.name)
            }
        ){
            Text(text = "Cerras Sesion")
        }
    }
}