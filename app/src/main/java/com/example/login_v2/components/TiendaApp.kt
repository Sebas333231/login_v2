package com.example.login_v2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_v2.pages.home.AddData
import com.example.login_v2.pages.home.GetData
import com.example.login_v2.pages.home.HomePage
import com.example.login_v2.pages.home.Home_Page2
import com.example.login_v2.pages.login.LoginScreen

enum class PagesScreen(){
    Start,
    DashBoard,
    Get,
    Add,
    rool
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 85.dp)
            ) {
                Text(
                    text = "Tienda Sena",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color(0xFF4CAF50),
                        fontSize = 30.sp
                    )
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = {navigateUp}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PagesScreen.Start.name
    ){
        composable(route = PagesScreen.Start.name){
            LoginScreen(navController)
        }
        composable(PagesScreen.DashBoard.name){
            HomePage(navController = navController)
        }
        composable(PagesScreen.Get.name){
            GetData(navController = navController)
        }
        composable(PagesScreen.Add.name){
            AddData(navController = navController)
        }
        composable(PagesScreen.rool.name){
            Home_Page2(navController = navController)
        }
    }
}

@Composable
fun ScaffoldContent(//(1)
    padding: PaddingValues
) {
    Column(// (2)
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = padding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{// (3)

        }
    }
}