package es.upsa.mimo.cursocompose.ui.screens.itemList

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.material3.Badge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.upsa.mimo.cursocompose.ui.screens.itemList.components.ItemList
import es.upsa.mimo.cursocompose.ui.screens.itemList.components.ItemMenu
import es.upsa.mimo.cursocompose.ui.screens.itemList.components.MyBottomNavigationBar
import es.upsa.mimo.cursocompose.ui.screens.itemList.components.MyTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListComplete(viewModel: ItemListViewModel = viewModel()) {
// ____________________________________ ____________________________________ ____________________________________

    // - 1. Tipos de 'scrollBehavior', como se ve el 'topBar' según vas desplazando la lista ('ItemList')
    // enterAlwaysScrollBehavior(): para que cuando se suba vaya apareciendo lentamente
    // pinnedScrollBehavior(): se mantiene toco el tiempo en la parte superior
    // exitUntilCollapsedScrollBehavior(): habria que usar una LargeTopAppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    // - 2. Elección de tipo de listado, seleccionable desde el 'bottomBar'. Lógica pasada al en el View Model
    // var selectedType by rememberSaveable { mutableStateOf(Type.CAT) }

    // - 3. El toast, definido como quiere que salga (es como un mini pop up).
    val snackbarHostState = remember { SnackbarHostState() } // Ojo solo puede ser 'remember', porque el scope es 'remember'
    val scope = rememberCoroutineScope()  //  para funciones 'suspend' como 'showSnackbar'. Permite lanzar tareas asíncronas dentro de un 'Composable' sin necesidad de usar un 'ViewModel'.

    // - 4. Desplazamiento del menú por defecto (en el 'topBar')
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // que este cerrado al entrar a la vista

// ____________________________________ ____________________________________ ____________________________________

    ModalNavigationDrawer( // Contener el desplazamiento del menú (el icono en el 'topBar' a la izquierda de las 3 rallitas)
        drawerContent = {
            ModalDrawerSheet{// Contenido del menú (hay que abrirlo desde el icono que se encuentra en el 'TopBar')
                ItemMenu()
            }
        },
        drawerState = drawerState // Tipo de estado de desplazamiento del menú
    ) {
        // Es un contenedor de UI de alto nivel. Facilita la integración de componentes como el 'topBar', 'bottomBar', 'snackbarHost' y 'floatingActionButton'
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // como se ve el 'topBar' según vas desplazando la lista ('ItemList')

            topBar = {
                MyTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onNavigationClick = {
                        scope.launch {
                            drawerState.open() // es una función 'suspend'
                        }
                    })
             },

            bottomBar = {
                MyBottomNavigationBar(
                    selectedType = viewModel.state.selectType,
                    onTypeClick = { viewModel.onTypeChange(it) } // { selectedType = it }
                )
            },

            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)  // El toast, optimo para notificaciones, como añadir a fav items, la logica se ejecuta al pulsar en un elemento del listado, explciado abajo en el cuerpo
            },

            floatingActionButton = { // Es el boton flotante
                FloatingActionButton(onClick = {}) {
                    Row (Modifier.padding(5.dp)) {
                        Text("Pulsa", Modifier.padding(end = 10.dp))
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            }

        ) {
            Box(modifier = Modifier.padding(it)) {

                ItemList(
                    items = viewModel.state.items, // items(selectedType)

                    onItemClick = { item ->
                        // Se podria navegar si se quisiese por ejemplo a otra vista con los detalles de dicha vista...
                        handleOnClickItem(item, scope, snackbarHostState)
                    }
                )
            }
        }
    }
}

private fun handleOnClickItem(item: Item, scope: CoroutineScope, snackbarHostState: SnackbarHostState){
    scope.launch {// Inicia una corrutina en el CoroutineScope que acabamos de crear.
        snackbarHostState.currentSnackbarData?.dismiss() // Si hay un Snackbar visible, lo cierra antes de mostrar uno nuevo.

        val result = snackbarHostState.showSnackbar(
            message = item.title,
            withDismissAction = true,
            actionLabel = "Undo",
            duration = SnackbarDuration.Short
        )

        // Posibles acciones del SnackbarHost
        when (result) {
            SnackbarResult.Dismissed -> {} // Si se rechaza (con la cruz) no hace nada.
            SnackbarResult.ActionPerformed -> { // Si se pulsa (al de aceptar) realiza una nueva acción
                snackbarHostState.showSnackbar("La acción que tu quieras añadir")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun ItemListCompletePreview() {
    ItemListComplete()
}