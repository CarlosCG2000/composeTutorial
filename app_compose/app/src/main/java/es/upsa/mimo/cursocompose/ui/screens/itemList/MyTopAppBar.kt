package es.upsa.mimo.cursocompose.ui.screens.itemList

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior?, onNavigationClick:() -> Unit) {
    TopAppBar( // CenterAlignedTopAppBar, MediumTopAppBar, LargeTopAppBar

        title = { Text("Mi lista Lazy") },
        navigationIcon = {
           IconButton(onClick = onNavigationClick) {
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = null)
        }},
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun TopAppBarPreview() {
    MyTopAppBar(null, {})
}

