package es.upsa.mimo.cursocompose.ui.screens.itemList.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import es.upsa.mimo.cursocompose.ui.screens.itemList.Item
import es.upsa.mimo.cursocompose.ui.screens.itemList.Type
import androidx.compose.foundation.lazy.items
import es.upsa.mimo.cursocompose.ui.screens.itemList.items

@Composable
fun ItemList(items: List<Item>, onItemClick: (Item) -> Unit /** al pulsar en una fila (item) */) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(items) { item ->

            // Una fila engobla el item
            Row(modifier =  Modifier.padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable { onItemClick(item) },
                verticalAlignment = Alignment.CenterVertically) {

                AsyncImage( model = item.thumb,
                            contentDescription = "Alt de la imagen",
                            contentScale = ContentScale.Crop,
                            modifier =  Modifier.size(40.dp)
                                                .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Dentro de la fila hay una columna
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(item.title, fontSize = 16.sp, fontWeight = W700)
                    Text(item.subtitle)
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Composable
fun ItemListPreview() {
    ItemList(items(Type.CAT), {})
}