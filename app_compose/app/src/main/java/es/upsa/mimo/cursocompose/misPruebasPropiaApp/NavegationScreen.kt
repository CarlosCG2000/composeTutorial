package es.upsa.mimo.cursocompose.misPruebasPropiaApp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.characterSection.CharacterFilterScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.characterSection.CharactersFavScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.menuSection.MenuScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.QuotesScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesFilterScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesFavScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.characterSection.CharactersScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodeDetailScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.profileSection.ProfileScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.episodeSection.EpisodesScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.profileSection.profileEdit.ProfileEditScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.QuotesFavScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.QuotesFilterScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.gameQuotes.QuotesGameScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.gameQuotes.QuotesQuestionScreen
import es.upsa.mimo.cursocompose.misPruebasPropiaApp.quoteSection.gameQuotes.QuotesResultScreen

// ✅ 1. Crear un sealed class para las rutas: en lugar de escribir las rutas como strings, se pueden definir en una sealed class (con parámetros si es necesario):
// • Evita errores tipográficos en las rutas.
// • Permite una navegación más clara y estructurada.
private sealed class Screen(val route: String) {
    object Menu : Screen("menu")

    object Profile : Screen("profileScreen")
    object ProfileEdit : Screen("profileEditScreen")

    object AllCharacters : Screen("navigateToAllCharacter")
    object FilterCharacters : Screen("navigateToFilterCharacter")
    object FavoriteCharacters : Screen("navigateToFavoriteCharacter")

    object AllEpisodes : Screen("navigateToAllEpisodes")
    object FilterEpisodes : Screen("navigateToFilterEpisode")
    object FavoriteEpisodes : Screen("navigateToFavoriteEpisode")
    object EpisodeDetailStatic: Screen("episodeDetail/{id}")
    data class EpisodeDetail(val id: Int) : Screen("episodeDetail/$id") // Pasando parámetros en la ruta

    object MainQuotes: Screen("navigateToAllQuote")
    object FilterQuotes : Screen("navigateToFilterQuotes")
    object FavoriteQuotes : Screen("navigateToFavoriteQuotes")
    object GameQuotes : Screen("navigateToGameQuotes")
    object QuestionQuotes : Screen("navigateToQuestionQuotes")
    object ResultQuotes : Screen("navigateToResultQuotes")
}

// ❌ Definición de destinos a través de objetos o clases (con parámetros que representan propiedades)
/** @Serializable
//class EpisodeDetailScreenDestination(val id: Int) // Destino de la vista de una de episodio en detalle
*/

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()

    // ######################### TOTAL SCREENS 16 #########################
    NavHost(navController = navController,
        startDestination = "menu"){

        //________________________ MENU (SCREEN 1) ________________________
        composable(Screen.Menu.route) {
            MenuScreen(
                onUserProfile = { navController.navigate(Screen.Profile.route) },
                navigateToCharacters = { navController.navigate(Screen.AllCharacters.route) },
                navigateToEpisodes = { navController.navigate(Screen.AllEpisodes.route) },
                navigateToQuotes = { navController.navigate(Screen.MainQuotes.route) }
            )
        }

        //________________________ PROFILE (SCREENS 2) ________________________
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigationProfileForm = { navController.navigate(Screen.ProfileEdit.route) }
            )
        }

        composable(Screen.ProfileEdit.route) {
            ProfileEditScreen(
                onLogin = { navController.navigate(Screen.Profile.route) }
            )
        }

        //________________________ CHARACTER (SCREENS 3) ________________________
        composable(Screen.AllCharacters.route) {
            CharactersScreen(
                navigateToFilterCharacters = { navController.navigate(Screen.FilterCharacters.route) },
                navigateToFavoriteCharacters = { navController.navigate(Screen.FavoriteCharacters.route) },
            )
        }

        composable(Screen.FilterCharacters.route) {
            CharacterFilterScreen(
                navigateToAllCharacters = { navController.navigate(Screen.AllCharacters.route) },
                navigateToFavoriteCharacters = { navController.navigate(Screen.FavoriteCharacters.route) },
            )
        }

        composable(Screen.FavoriteCharacters.route) {
            CharactersFavScreen(
                navigateToAllCharacters = { navController.navigate(Screen.AllCharacters.route) },
                navigateToFilterCharacters = { navController.navigate(Screen.FilterCharacters.route) }
            )
        }

        //________________________ EPISODE (SCREENS 4) ________________________
        composable(Screen.AllEpisodes.route) {
            EpisodesScreen(
                navigateToFilterEpisode = { navController.navigate(Screen.FilterEpisodes.route) },
                navigateToFavoriteEpisode = { navController.navigate(Screen.FavoriteEpisodes.route) },
                onEpisodeSelected = { id -> navController.navigate(Screen.EpisodeDetail(id).route) }
            )
        }

        composable(Screen.FilterEpisodes.route) {
            EpisodesFilterScreen(
                navigateToAllEpisodes = { navController.navigate(Screen.AllEpisodes.route) },
                navigateToFavoriteEpisode = { navController.navigate(Screen.FavoriteEpisodes.route) },
                onEpisodeSelected = { id -> navController.navigate(Screen.EpisodeDetail(id).route) }
            )
        }

        composable(Screen.FavoriteEpisodes.route) {
            EpisodesFavScreen(
                navigateToAllEpisodes = { navController.navigate(Screen.AllEpisodes.route) },
                navigateToFilterEpisode = { navController.navigate(Screen.FilterEpisodes.route) },
                onEpisodeSelected = { id -> navController.navigate(Screen.EpisodeDetail(id).route) }
            )
        }

        // ✅ 2. Antes pasaba el id del episodio a la 'EpisodeDetailScreen' con Serializable y toRoute(), lo cual es una estrategia válida, pero puede simplificarse usando la navegación de 'Jetpack Compose' de forma nativa.  Usando 'NavArgument' para pasar parámetros.
        composable( route = Screen.EpisodeDetailStatic.route,
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
            EpisodeDetailScreen(id = id)
        }

        /**
                composable<EpisodeDetailScreenDestination>{  navBackStackEntry /* destino */ ->
                    val episodeDetailScreenDestination: EpisodeDetailScreenDestination = navBackStackEntry.toRoute() // Obtenemos el objeto

                    val id: Int = episodeDetailScreenDestination.id

                    EpisodeDetailScreen(id = id)
                }
        */

        //________________________ QUOTE AND GAME (SCREENS 4-2) ________________________
        composable(Screen.MainQuotes.route) {
            QuotesScreen(
                navigateToFilterQuotes = { navController.navigate(Screen.FilterQuotes.route) },
                navigateToFavoriteQuotes = { navController.navigate(Screen.FavoriteQuotes.route) },
                navigateToGameQuotes = { navController.navigate(Screen.GameQuotes.route) }
            )
        }

        composable(Screen.FilterQuotes.route) {
            QuotesFilterScreen(
                navigateToQuotes = { navController.navigate(Screen.MainQuotes.route) },
                navigateToFavoriteQuotes = { navController.navigate(Screen.FavoriteQuotes.route) },
                navigateToGameQuotes = { navController.navigate(Screen.GameQuotes.route) }
            )
        }

        composable(Screen.FavoriteQuotes.route) {
            QuotesFavScreen(
                navigateToQuotes = { navController.navigate(Screen.MainQuotes.route) },
                navigateToFilterQuotes = { navController.navigate(Screen.FilterQuotes.route) },
                navigateToGameQuotes = { navController.navigate(Screen.GameQuotes.route) }
            )
        }

        composable(Screen.GameQuotes.route) {
            QuotesGameScreen(
                navigateToQuotes = { navController.navigate(Screen.MainQuotes.route) },
                navigateToFilterQuotes = { navController.navigate(Screen.FilterQuotes.route) },
                navigateToFavoriteQuotes = { navController.navigate(Screen.FavoriteQuotes.route) },
                navigateToQuestionQuotes = { navController.navigate(Screen.QuestionQuotes.route) }
            )
        }

        composable( Screen.QuestionQuotes.route) {
            QuotesQuestionScreen(
                navigateToResultQuotes = { navController.navigate(Screen.ResultQuotes.route) }
            )
        }

        composable(Screen.ResultQuotes.route) {
            QuotesResultScreen(
                navigateToQuotes = { navController.navigate(Screen.MainQuotes.route) }
            )
        }
    }
}

/**
@Composable
private fun episodeSelectId(navController: NavHostController): (Int) -> Unit = { id ->
    val navigateToDetailEpisode = EpisodeDetailScreenDestination(id = id)
    navController.navigate(navigateToDetailEpisode) // Navegamos a la pantalla de detalles
}
*/