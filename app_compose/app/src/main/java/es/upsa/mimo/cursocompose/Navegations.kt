package es.upsa.mimo.cursocompose

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.upsa.mimo.cursocompose.ui.screens.itemList.ItemListComplete
import es.upsa.mimo.cursocompose.ui.screens.login.LoginForm

@Composable
fun MyNavegacion() {

    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = "login"){

        composable(route = "login") {
            LoginForm(onLogin = { navController.navigate("itemList") })
        }

        composable(route = "itemList") {
            ItemListComplete()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun MyNavegacionPreview() {
        MyNavegacion()
}
