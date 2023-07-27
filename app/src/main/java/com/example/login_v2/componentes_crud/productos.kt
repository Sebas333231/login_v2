package com.example.login_v2.componentes_crud

data class productos(
    var productoID: String = "",
    var imagen: String = "",
    var nombre: String = "",
    var precio: Int = 0,
    var descripcion: String = ""
)
