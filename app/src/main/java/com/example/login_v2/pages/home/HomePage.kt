package com.example.login_v2.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login_v2.components.PagesScreen
import com.example.login_v2.components.navigations
import com.example.login_v2.pages.login.LoginScreenViewModel

@Composable
fun HomePage(
    navController: NavController,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                navController.navigate(PagesScreen.Get.name)
            }
        ){
            Text(text = "Get Producto")
        }
        Button(
            onClick = {
                navController.navigate(PagesScreen.Add.name)
            }
        ){
            Text(text = "Add Producto")
        }
        Button(onClick = {
            navController.popBackStack()
            navController.navigate(PagesScreen.Start.name)

             }) {
            Text(text = "Cerrar Sesion")
        }
    }
}

