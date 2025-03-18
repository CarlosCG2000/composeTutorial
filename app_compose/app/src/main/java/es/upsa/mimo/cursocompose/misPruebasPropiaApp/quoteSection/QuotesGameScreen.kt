package es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun QuotesGameScreen(
    navigateToQuotes: () -> Unit,
    navigateToFilterQuotes: () -> Unit,
    navigateToFavoriteQuotes: () -> Unit,
    navigateToGameQuotes: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBarQuoteComponent(
                selectedBarButtom = 4,
                navigateToQuotes= navigateToQuotes,
                navigateToFiltersQuotes= navigateToFilterQuotes,
                navigateToFavoritesQuotes = navigateToFavoriteQuotes,
                navigateToGameQuotes = navigateToGameQuotes
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
                verticalArrangement = Arrangement.Center, // Centra verticalmente dentro de Column
                horizontalAlignment = Alignment.CenterHorizontally
            ) { // Centra horizontalmente
                // LOGO SIMPSONS
                Text("NavegacionCitas", fontSize = 24.sp, fontWeight = Bold)
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun QuotesGameScreenPreview() {
    QuotesGameScreen({}, {}, {}, {})
}