package com.example.login_v2.pages.login

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_v2.R
import com.example.login_v2.components.CreateChannelNotification
import com.example.login_v2.components.PagesScreen
import com.example.login_v2.components.notificationExtensa



@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    // Variable para controlar la accion: True::Login - False::Create
    val navCotroller = navController
    val showLoginFrom = rememberSaveable{
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val customFont = FontFamily(
                Font(R.font.comicneue_regular),
                Font(R.font.comicneue_bold, FontWeight.Bold)
            )
            if(showLoginFrom.value){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp))
                Text(
                    text = "Iniciar Sesion",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(
                        0xFF4CAF50
                    ), fontFamily = customFont
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Disfruta de nuestros mejores productos",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(
                        0xFF4CAF50
                    ), fontFamily = customFont
                    )
                )
                Text(
                    text = "inicia sesion ¡ahora!",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(
                        0xFF4CAF50
                    ), fontFamily = customFont
                    )
                )
                UserForm(
                    valorBoolean = showLoginFrom.value,
                    navController = navCotroller,
                    isCreateAccount = false
                ){
                        email, password ->
                    Log.d("TiendaApp", "Iniciar session con $email y $password")
                }
            }else{
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp))
                Text(
                    text = "Create Cuenta Nueva",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(
                        0xFF4CAF50
                    ), fontFamily = customFont
                    )
                )
                UserForm(
                    valorBoolean = showLoginFrom.value,
                    navController = navController,
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("TiendaApp", "Creando cuenta con $email y $password")
                    viewModel.createUser(email, password){
                        navController.navigate(PagesScreen.DashBoard.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 =
                    if(showLoginFrom.value)
                        "¿No tienes cuenta?"
                    else
                        "Ya tienes cuenta"

                val text2 =
                    if(showLoginFrom.value)
                        "Registrarse"
                    else
                        "Iniciar Sesion"
                Text(
                    text = text1
                )
                Text(
                    text = text2,
                    modifier = Modifier
                        .clickable { showLoginFrom.value = !showLoginFrom.value }
                        .padding(start = 5.dp),
                    color = Color(0xFF4CAF50)
                )

            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    valorBoolean: Boolean,
    navController: NavController,
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = {email, pwd->}
) {
    val navCotroller = navController
    val email = remember{
        mutableStateOf("")
    }
    val password = remember{
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable{
        mutableStateOf(false)
    }
    val validState = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }


    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(modifier = Modifier.height(15.dp))
        SubmitButtom(
            //roolAdmin = rool.value,
            valorInicio = valorBoolean,
            textId =
            if(isCreateAccount)
                "Crear Cuenta"
            else
                "Iniciar Session",
            inputValid = validState,
            password = password.value,
            email = email.value,
            comprobante = isCreateAccount,
            navController = navController
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButtom(
    //roolAdmin: String,
    valorInicio: Boolean,
    navController: NavController,
    comprobante: Boolean,
    password: String,
    email: String,
    textId: String,
    inputValid: Boolean,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onClick: () -> Unit,
) {
    val cuenta = if(comprobante == true){
        "Bienvenido"
    }else{
        "Iniciaste Sesion"
    }

    val almacena = if (comprobante == true){
        "Tu cuenta '$email' fue creada correctamente."
    }else{
        "Has iniciado correctamente con tu correo '$email'"
    }
    val idNotication: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = "Canal Tienda"
    val name: String = "Canal Tienda"
    val descriptionText: String = "Canal de Notificaciones Tienda CBA"
    val textLong: String = "Saludos! Esta es una prueba de notificaciones. Bebe aparecer " +
            "un icono a la derecha y el texto puede tener una longitud de 200 caracteres "+
            "Gracias y hasta pronto"
    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.logo
    )
    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.epa
    )

    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit){
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }
    Button(
        onClick = {
            notificationExtensa(
                context,
                idChannel,
                idNotication + 1,
                cuenta,
                almacena,
                iconoBig
            )
            onClick
            if(valorInicio == true){
                viewModel.signIn(email, password){
                    if(email == "sebas209@gmail.com"){
                        navController.navigate(PagesScreen.DashBoard.name)
                    }else{
                        navController.navigate(PagesScreen.rool.name)
                    }
                }
            }else{
              viewModel.createUser(email, password){
                      //navController.navigate(PagesScreen.DashBoard.name)
                  navController.navigate(PagesScreen.rool.name)
              }
            }
                  },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValid
    ) {
        val customFont = FontFamily(
            Font(R.font.comicneue_regular),
            Font(R.font.comicneue_bold, FontWeight.Bold)
        )
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(
                0xFF000000), fontFamily = customFont
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation =
        if(passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if(passwordState.value.isNotBlank())
                PasswordVisibleIcon(passwordVisible)
            else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(
        onClick = {
            passwordVisible.value = !passwordVisible.value
        }
    ) {
        Icon(imageVector = image, contentDescription = "")
    }
}


@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email",
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email,
    )
}

@Composable
fun Rool(
    roolState: MutableState<String>,
    labelId: String = "rool"
){
    InputField(
        valueState = roolState,
        labelId = labelId,
        keyboardType = KeyboardType.Text
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingLine: Boolean = true,
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange ={valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
    )
}