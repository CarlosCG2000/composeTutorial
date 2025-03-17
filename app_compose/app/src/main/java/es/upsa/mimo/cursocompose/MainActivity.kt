package es.upsa.mimo.cursocompose

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.NavegacionApp
import es.upsa.mimo.cursocompose.ui.screens.itemList.Item
import es.upsa.mimo.cursocompose.ui.theme.CursoComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
//            CursoComposeTheme { // El tema que va a tener toda mi app
//                Screen {
//            Scaffold {
              // MyNavegacion()
            NavegacionApp()
//            }
                }
//            }
//        }
    }
}

/** 1. Ejercicio: Crea un botón que muestre un mensaje pasado por argumento al Composable, y que muestre un Toast al hacer click.*/
@Composable
fun MyButton(message: String) {
    // Contexto que hay que darle al 'Toast'.
    val context =  LocalContext.current // El contexto tiene que estar dentro de una UI (Compose), si intentamos definirlo dentro del `makeText` dara error porque ya no se encontraria en una UI.

    Button(
        onClick = {
            Toast.makeText(context /** LocalContext.current */,
                            message,
                            Toast.LENGTH_SHORT).show()
                  },
        modifier = Modifier.padding(top = 300.dp, start = 150.dp)
    ) {
        Icon(imageVector = Icons.Default.Email, contentDescription = "Icono del email")
        Spacer(modifier = Modifier.width(4.dp) )
        Text("Enviar")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyBox(){
    Box( modifier = Modifier.fillMaxSize()
                            .background(Color.Cyan),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Hello", fontFamily = FontFamily.SansSerif, fontSize = 35.sp)
        Text("Goodbye", Modifier.align(Alignment.CenterEnd).padding(end = 20.dp))
    }
}

@Composable
fun MyColumn(){ // En el Row seria similar
    Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly, // .SpaceBetween, .Center,
            horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Text("Hello", /*modifier = Modifier.weight(2f)*/)
        Text("Goodbye")

        repeat(10){
            Text(text = "Item $it")
        }
    }
}

/** 2. Ejercicio: Crea una columna con 2 TextFields y un botón. */
@Composable
fun SimpleLogin() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = "user", onValueChange = {})

        TextField(value = "password", onValueChange = {})

        Button(onClick = {}){ Text("Registrar")}
    }
}

@Composable
fun MyTextButton() {
        Text(
            text = "Hello Button",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize() // lo que mida le texto
                .clickable {}

                // Crear un borde y un fondo
                .border(2.dp, Color.Blue)
                .background(Color.Cyan)
                .padding(30.dp)

                // Crear otro borde con otro fondo, viendo se ahora ambos
                .border(2.dp, Color.Red)
                .background(Color.LightGray)
                .padding(20.dp)
        )
}

@Composable
fun MyLoginState() {
    // Estado que hay que prooveer por defecto.
    // var user: String = "" // no funciona
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = user, onValueChange = { user = it } )

        TextField(value = password, onValueChange = { password = it })

        Button(onClick = {}){ Text("Registrar") }
    }
}

/** 3 Ejercicio: Crea un state para cada uno de los TextFields. Activa el botón solo si los dos campos tienen datos, y cuando se haga click, muestra un Toast y borra los campos. */
@Composable
fun MyLoginEnabled() {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(  value = user,
                    onValueChange = { user = it } )

        TextField(  value = password,
                    onValueChange = { password = it } )

        Button( onClick = {  user = ""; password = "" },
                enabled = (user.isNotEmpty() && password.isNotEmpty())
        ){ Text("Registrar") }
    }
}

/** 4 Ejercicio: Mejorar el ejercicio 3 añadiendo un icono y la comprobación de errores en el formulario. */
// Fichero 'LoginForm.kt' y 'LoginFormViewModel.kt'

// Se puede crear un Composable que recibia otro Composable para mostrarlos
@Composable
fun Screen(content: @Composable () -> Unit ) { // Vista principal que puede recibir otra vista secundaria por parametro
    
}

@Composable
fun MyLazyColumn() {

    val listItems: List<String> = (1..100).map { "Item $it" }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listItems){ item ->
            Text(item)
        }
    }
}

/** 5 Ejercicio: Crea un LazyColumn que pinte la vista anterior usando estos items definidos en 'Item.kt'. */
@Composable
fun SimpleLazyColumn(items: List<Item>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(items){ item ->

            Column(modifier = Modifier.padding(15.dp)) {
                Text(item.title)
                Text(item.subtitle)
            }
        }
    }
}

// Mi esqueleto y mi distribución
// ...
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
// @Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Modo Oscuro")
@Composable
fun GreetingPreview() {
    CursoComposeTheme {
        // MyButton("Android")
        // MyBox()
        // MyColumn()
        // Ejer2Login()
        // MyTextButton()
        // MyLogin()
        // Ejer3Login()
        // Ejer4ExtraLogin()
        // Screen(){ Ejer4ExtraLogin() }
        // MyLazyColumn()
        // Ejer5LazyColumn()
        // MyLazyImagen()
        // MyScaffold()
        MyNavegacion()
    }
}
