package es.upsa.mimo.cursocompose

import android.R.attr.enabled
import android.content.res.Configuration
import android.media.audiofx.Visualizer

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import es.upsa.mimo.cursocompose.ui.theme.CursoComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
//            CursoComposeTheme { // El tema que va a tener toda mi app
//                Screen {
//            Scaffold {
            MyNavegacion()
//            }
                }
//            }
//        }
    }
}

@Composable
fun MyButton(message: String) {

    val context =  LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT).show()
                  },
        modifier = Modifier.padding(top = 300.dp)
    ) {
        Icon(imageVector = Icons.Default.Email, contentDescription = "Icono de mail")
        Spacer(modifier = Modifier.width(4.dp) )
        Text(message)
    }
}

@Composable
fun MyBox(){
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Hello")
        Text("Goodbye", Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
fun MyColumn(){ // EL ROW ES IGUAL
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Text("Hello", /*modifier = Modifier.weight(2f)*/)
        Text("Goodbye")

        repeat(10){
            Text(text = "Item $it")
        }
    }
}

@Composable
fun Ejer2Login() {
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

                .border(2.dp, Color.Blue)
                .background(Color.Cyan)
                .padding(30.dp)

                .border(2.dp, Color.Red)
                .background(Color.LightGray)
                .padding(20.dp)
        )
}

@Composable
fun MyLogin() {
    // Estado que hay que prooveer por defecto.
    // var user: String = "" // no funciona
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = user, onValueChange = { user = it } )

        TextField(value = password, onValueChange = { password = it})

        Button(onClick = {}){ Text("Registrar")}
    }
}

@Composable
fun Ejer3Login() {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = user, onValueChange = { user = it } )

        TextField(value = password, onValueChange = { password = it})

        Button(onClick = {
                    user = ""
                    password = ""
                },
                enabled = (user.isNotEmpty() && password.isNotEmpty())
        ){ Text("Registrar")}
    }
}

// FORMULARIO
// Fichero LoginForm.kt --> Ejer4ExtraLogin, PassVisibleIcon, Ejer4ExtraLoginPreview

@Composable
fun Screen(content: @Composable () -> Unit ) { // vista principal que puede recibir otra vista
    
}

@Composable
fun MyLazyColumn() {

    val listItems: List<String> = (1..100).map { "Item $it" }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listItems.size){ index ->
            Text(listItems[index])
        }
    }
}

@Composable
fun Ejer5LazyColumn() {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(items.size){ index ->

            Column(modifier = Modifier.padding(15.dp)) {
                Text(items[index].title)
                Text(items[index].subtitle)
            }
        }
    }
}

// 'fun MyLazyImagen()' en 'Itemlist.kt'

// 'fun MyScaffold()' en 'ItemListScaffold.kt'

// 'fun MyNavegacion()' en 'Navegacions'

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
