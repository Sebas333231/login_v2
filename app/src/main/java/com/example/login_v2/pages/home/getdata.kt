package com.example.login_v2.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login_v2.componentes_crud.ProductosModel
import com.example.login_v2.componentes_crud.productos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetData(
    navController: NavController,
    viewModel: ProductosModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var productoID: String by remember { mutableStateOf("") }
    var nombre: String by remember { mutableStateOf("") }
    var imagen: String by remember { mutableStateOf("") }
    var precioInt: Int by remember {
        mutableStateOf(0)
    }
    var precio: String by remember { mutableStateOf("") }
    var descripcion: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Button")
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier.width(145.dp),
                    value = productoID,
                    onValueChange = {
                        productoID = it
                    },
                    label = {
                        Text(text = "Producto ID")
                    }
                )
                Button(
                    onClick = {
                        viewModel.editData(productoID = productoID, context = context){
                                data ->
                            nombre = data.nombre
                            descripcion = data.descripcion
                            imagen = data.imagen
                            precio = data.precio.toString()
                        }
                    },
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp)
                ){
                    Text(text = "Buscar")
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nombre,
                onValueChange = {
                    nombre = it
                },
                label = {
                    Text(text = "Nombre")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descripcion,
                onValueChange = {
                    descripcion = it
                },
                label = {
                    Text(text = "Descripcion")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = imagen,
                onValueChange = {
                    imagen = it
                },
                label = {
                    Text(text = "Imagen")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = precio,
                onValueChange = {
                    precio = it
                    if(precio.isNotEmpty()){
                        precioInt = precio.toInt()
                    }
                },
                label = {
                    Text(text = "Precio")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val productoData = productos(
                        productoID = productoID,
                        nombre = nombre,
                        imagen = imagen,
                        precio = precioInt,
                        descripcion = descripcion
                    )
                    viewModel.saveData(productoData = productoData, context = context)
                }
            ){
                Text(text = "Guardar")
            }
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.deleteData(productoID = productoID, context = context, navController = navController)
                }
            ){
                Text(text = "Borrar")
            }
        }
    }
}