package com.example.login_v2.componentes_crud

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductosModel: ViewModel() {
    fun saveData(
        productoData: productos,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore

            .collection("productos")
            .document(productoData.productoID)

        try{
            fireStoreRef.set(productoData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show()
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message ,Toast.LENGTH_LONG).show()
        }
    }
    fun editData(
        productoID: String,
        context: Context,
        data: (productos) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore

            .collection("productos")
            .document(productoID)

        try{
            fireStoreRef.get()
                .addOnSuccessListener {
                    //Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show()
                    if (it.exists()){
                        val productData = it.toObject<productos>() !!
                        data(productData)
                    }else{
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message ,Toast.LENGTH_LONG).show()
        }
    }
    fun deleteData(
        productoID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("productos")
            .document(productoID)

        try{
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message ,Toast.LENGTH_LONG).show()
        }
    }
}