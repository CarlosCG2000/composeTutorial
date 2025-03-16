package es.upsa.mimo.cursocompose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold() {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    // enterAlwaysScrollBehavior(): para que cuando se suba vaya apareciendo lentamente
    // pinnedScrollBehavior(): se mantiene toco el tiempo en la parte superior
    // exitUntilCollapsedScrollBehavior: habria que usar una LargeTopAppBar

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // para la topBar
        topBar = {
            MyTopAppBar(scrollBehavior)
        },
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
            MyLazyImagen()
        }

    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun MyScaffoldPreview() {
    MyScaffold()
}