package com.example.login_v2.pages.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_v2.components.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth // variable auth que usaremos
    private val _loading = MutableLiveData(false)// variable que impide que se creen varios usuarios

    fun signIn(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if(task.isSuccessful){
                        Log.d("Epaa", "SignInWithEmailAndPassword: Logradooooo!!!!!")
                        home()
                    }else{
                        Log.d("Noooo", "SignInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }

        }catch (ex: Exception){
            Log.d("Tienda", "SignInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUser(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if(_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if(task.isSuccessful){
                        val displatName = task.result.user?.email?.split("@")?.get(0)
                        val rool = "Cliente"
                        createUserName(displatName, rool = rool)

                        home()
                    }
                    else{
                        Log.d("Epaaaaa", "crateUserEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }
    private fun createUserName(displayName: String?, rool: String){
        val userId = auth.currentUser?.uid
        //val user = mutableMapOf<String, Any>()

        //user["user_id"] = userId.toString()
        //user["display_Name"] = displayName.toString()
        val user = Data(
            UserId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            profession = "Estudiante",
            rool = rool,
            id = null,
        ).toMap()


        FirebaseFirestore.getInstance().collection("usuarios")
            .add(user)
            .addOnSuccessListener {
                Log.d("Epaaaaaaaaa", "Creando Usuario en la coleccion ${it.id}")
            }.addOnFailureListener {
                Log.d("Epaaaaaaaaa", "Ocurrio un error ${it}")
            }
    }
}