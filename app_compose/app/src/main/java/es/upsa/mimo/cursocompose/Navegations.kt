package es.upsa.mimo.cursocompose

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.upsa.mimo.cursocompose.ui.theme.CursoComposeTheme

@Composable
fun MyNavegacion() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "login"){

        composable(route = "login") {
            Ejer4ExtraLogin(onLogin = { navController.navigate("itemList")})
        }

        composable(route = "itemList") {
            MyScaffold()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun MyNavegacionPreview() {
        MyNavegacion()
}
