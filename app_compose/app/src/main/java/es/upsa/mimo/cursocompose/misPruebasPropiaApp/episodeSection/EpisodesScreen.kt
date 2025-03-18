package es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.BottomBarComponent

@Composable
fun EpisodesScreen(selectedBarButtom:Int = 1,
                   navigateToAllEpisodes: () -> Unit,
                   navigateToFilterEpisode: () -> Unit,
                   navigateToFavoriteEpisode: () -> Unit,
                   onEpisodeSelected: (Int) -> Unit) {

    val listItems: List<String> = (1..50).map { "Item $it" }

    Scaffold(
            bottomBar = {
                BottomBarComponent(
                    selectedBarButtom,
                    navigateToAllEpisodes,
                    navigateToFilterEpisode,
                    navigateToFavoriteEpisode
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()
                                    .padding(paddingValues)
                                    .background(Color.Red),
                contentAlignment = Alignment.Center) {

                Column(modifier = Modifier.fillMaxSize().padding(top = 60.dp), // Ocupa toda la pantalla
                    //verticalArrangement = Arrangement.Center, // Centra verticalmente dentro de Column
                   // horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
                    ){
                    // LOGO SIMPSONS
                    Text("NavegacionEpisodios", fontSize = 24.sp, fontWeight = Bold)

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(listItems) { index, item ->
                            Text(
                                text = item,
                                modifier = Modifier.clickable {
                                    onEpisodeSelected(index+1) // Ahora puedes obtener la posición del item
                                }.padding(20.dp)
                            )
                        }
                    }

                }
            }
        }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun EpisodesScreenPreview() {
    Column {
        EpisodesScreen(1, {}, {}, {}, {})
    }
}
