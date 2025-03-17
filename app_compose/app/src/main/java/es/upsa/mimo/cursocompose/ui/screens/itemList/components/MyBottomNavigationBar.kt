package es.upsa.mimo.cursocompose.ui.screens.itemList.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import es.upsa.mimo.cursocompose.ui.screens.itemList.Type

@Composable
fun MyBottomNavigationBar(selectedType: Type, onTypeClick: (Type) -> Unit ) {
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

    NavigationBar { // Barra de navegación

        Type.values().forEach { type -> // Se recorren los tipos para mostrarlos

            NavigationBarItem( // Elemento de la barra de navegacion (la tenemos puesta en BottomBar)
                selected = type == selectedType, // solo marcamos como seleccionada a un tipo (al principio el por defecto)
                onClick = { onTypeClick(type) }, // al dar click se camviara al listado del tipo correpondiente (es un lambda se podria hacer otra cosas como navgar a una pantanlla diferente
                icon = {
                    Icon(   imageVector = type.icon,
                        contentDescription = "Icono del tipo de Item")
                },
                label = { Text(text = type.title) }
            )
        }
    }

}
