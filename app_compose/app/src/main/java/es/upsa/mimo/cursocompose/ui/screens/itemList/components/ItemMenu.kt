package es.upsa.mimo.cursocompose.ui.screens.itemList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )

    Spacer(modifier = Modifier.height(16.dp))

    NavigationDrawerItem(
        label = { Text("Home") },
        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
        selected = true,
        onClick = {})

    NavigationDrawerItem(
        label = { Text("Upgrade") },
        icon = { Icon(imageVector = Icons.Default.Upgrade, contentDescription = null) },
        badge = { Badge() { Text("3") } },
        selected = false,
        onClick = {})

    NavigationDrawerItem(
        label = { Text("Setting") },
        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
        selected = false,
        onClick = {})
}
