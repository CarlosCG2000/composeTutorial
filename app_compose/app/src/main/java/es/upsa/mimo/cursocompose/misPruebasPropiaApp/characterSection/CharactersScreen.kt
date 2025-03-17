package es.upsa.mimo.cursocompose.misPruebasPropiaApp.characterSection

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CharactersScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
            verticalArrangement = Arrangement.Center, // Centra verticalmente dentro de Column
            horizontalAlignment = Alignment.CenterHorizontally){ // Centra horizontalmente
            // LOGO SIMPSONS
            Text("NavegacionPersonajes", fontSize = 24.sp, fontWeight = Bold)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun CharactersScreenPreview() {
    Column {
        CharactersScreen()
    }
}