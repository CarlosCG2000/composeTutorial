package es.upsa.mimo.cursocompose.misPruebasPropiaApp

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarComponent(title: String, onNavigationArrowBack:() -> Unit /**Para el el icono del menú*/) {

    CenterAlignedTopAppBar( // Puede ser tambien: CenterAlignedTopAppBar, MediumTopAppBar, LargeTopAppBar

        title = { Text(title) },

        navigationIcon = { // Icono del menú
            IconButton(onClick = onNavigationArrowBack) {
                Icon(   imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Icono de regreso al menú")
                }
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun TopBarComponentPreview() {
    TopBarComponent("Pantalla Extra", {})
}

