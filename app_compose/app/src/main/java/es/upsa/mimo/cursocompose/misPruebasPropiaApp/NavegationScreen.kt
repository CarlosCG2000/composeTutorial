package es.upsa.mimo.cursocompose.misPruebasPropiaApp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.menuSection.MenuScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.QuotesScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesFilterScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesFavScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.characterSection.CharactersScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodeDetailScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.profileSection.ProfileScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.profileSection.profileEdit.ProfileEditScreen
import kotlinx.serialization.Serializable

// Definición de destinos a través de objetos o clases (con parámetros que representan propiedades)
@Serializable
class EpisodeDetailScreenDestination(val id: Int) // Destino de la vista de una de episodio en detalle

//@Serializable
//class GameFiveQuestionsScreenDestination(val questions: List<>)

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()

    // ######################### TOTAL SCREENS 16 #########################
    NavHost(navController = navController,
        startDestination = "menu"){

        //________________________ MENU (SCREEN 1) ________________________
        composable(route = "menu") {
            MenuScreen(
                onUserProfile = { navController.navigate("profileScreen") },
                navigateToCharacters = { navController.navigate("navigateToAllCharacter") },
                navigateToEpisodes = { navController.navigate("navigateToAllEpisodes") },
                navigateToQuotes = { navController.navigate("navigateToAllQuote") }
            )
        }

        //________________________ PROFILE (SCREENS 2) ________________________
        composable(route = "profileScreen") {
            ProfileScreen(
                onNavigationProfileForm = { navController.navigate("profileEditScreen") }
            )
        }

        composable(route = "profileEditScreen") {
            ProfileEditScreen(
                onLogin = { navController.navigate("profileScreen") }
            )
        }

        //________________________ CHARACTER (SCREENS 3) ________________________
        composable(route = "navigateToAllCharacter") {
            CharactersScreen()
        }

        //________________________ EPISODE (SCREENS 4) ________________________
        composable(route = "navigateToAllEpisodes") {
            EpisodesScreen(
                navigateToAllEpisodes = { },
                navigateToFilterEpisode = { navController.navigate("navigateToFilterEpisode") },
                navigateToFavoriteEpisode = { navController.navigate("navigateToFavoriteEpisode") },
                onEpisodeSelected = episodeSelectId(navController)
            )
        }

        composable(route = "navigateToFilterEpisode") {
            EpisodesFilterScreen(
                navigateToAllEpisodes = { navController.navigate("navigateToAllEpisodes") },
                navigateToFilterEpisode = { },
                navigateToFavoriteEpisode = { navController.navigate("navigateToFavoriteEpisode") },
                onEpisodeSelected = episodeSelectId(navController)
            )
        }

        composable(route = "navigateToFavoriteEpisode") {
            EpisodesFavScreen(
                navigateToAllEpisodes = { navController.navigate("navigateToAllEpisodes") },
                navigateToFilterEpisode = { navController.navigate("navigateToFilterEpisode") },
                navigateToFavoriteEpisode = { },
                onEpisodeSelected = episodeSelectId(navController)
            )
        }

        composable<EpisodeDetailScreenDestination>{  navBackStackEntry /* destino */ ->
            val episodeDetailScreenDestination: EpisodeDetailScreenDestination = navBackStackEntry.toRoute() // Obtenemos el objeto

            val id: Int = episodeDetailScreenDestination.id

            EpisodeDetailScreen(id = id)
        }

        //________________________ QUOTE AND GAME (SCREENS 4-2) ________________________
        composable(route = "navigateToAllQuote") {
            QuotesScreen()
        }

    }
}

@Composable
private fun episodeSelectId(navController: NavHostController): (Int) -> Unit = { id ->
    val navigateToDetailEpisode = EpisodeDetailScreenDestination(id = id)
    navController.navigate(navigateToDetailEpisode) // Navegamos a la pantalla de detalles
}
