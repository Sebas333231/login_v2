package com.example.login_v2.components

sealed class navigations(val route: String){
    object SplashScreen: navigations("splash_screen")
    object MainScreen: navigations("tienda_app")
    object Get: navigations("get_product")
    object Add: navigations("Add_product")
    object Home: navigations("home")

}

sealed class Route(val route: String){
    object HomePage: Route("Home_Page")
}