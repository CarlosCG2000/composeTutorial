package es.upsa.mimo.cursocompose

import android.R.attr.contentDescription
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold() {

    // enterAlwaysScrollBehavior(): para que cuando se suba vaya apareciendo lentamente
    // pinnedScrollBehavior(): se mantiene toco el tiempo en la parte superior
    // exitUntilCollapsedScrollBehavior: habria que usar una LargeTopAppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var selectedType by rememberSaveable { mutableStateOf(Type.CAT) }

    val snackbarHostState = remember { SnackbarHostState() } // ojo solo puede ser remember
    val scope = rememberCoroutineScope()  // para 'fun showSnackbar' al ser 'suspend'.  Permite lanzar tareas asíncronas dentro de un Composable sin necesidad de usar un ViewModel.

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // para la topBar
        topBar = {  MyTopAppBar(scrollBehavior) },

        bottomBar = {
            BottomNavigation(
                selectedType = selectedType,
                onTypeClick = { selectedType = it }
            )
        },

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Row {
                    Text("Hola")
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = null)
                }
            }
        }

    ){
        Box(modifier = Modifier.padding(it)){

            MyLazyImagen(items = items (selectedType),

                onItemClick = { item ->
                            scope.launch {// Inicia una corutina en el CoroutineScope que acabamos de crear.
                                snackbarHostState.currentSnackbarData?.dismiss() // Si hay un Snackbar visible, lo cierra antes de mostrar uno nuevo.
                                val result = snackbarHostState.showSnackbar(
                                    message = item.title,
                                    withDismissAction = true,
                                    actionLabel = "Undo",
                                    duration = SnackbarDuration.Short)

                                when(result){
                                    SnackbarResult.Dismissed -> { }
                                    SnackbarResult.ActionPerformed -> { snackbarHostState.showSnackbar("Action perfomed")  }
                                }
                            }
                }
            )
        }
    }
}

@Composable
fun BottomNavigation(selectedType: Type, onTypeClick: (Type) -> Unit ) {

    NavigationBar {

        Type.values().forEach { type ->

        NavigationBarItem(
                selected = type == selectedType,
                onClick = { onTypeClick(type) },
                icon = {
                    Icon(
                        imageVector = type.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = type.title)
                }
            )
        }
    }

    //    BottomAppBar(tonalElevation = 5.dp) {
//        IconButton(onClick = {}) {
//            Icon(imageVector = Icons.Default.Search, contentDescription = null)
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(onClick = {}) {
//            Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
//        }
//    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun MyScaffoldPreview() {
    MyScaffold()
}