package es.upsa.mimo.cursocompose

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun MyLazyImagen(items: List<Item>, onItemClick: (Item) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        Log.e("MyLazyImagen","Número de items ${items.count()}")

        items(items.count()) { index ->

            if(index == 5){
                Log.e("MyLazyImagen","Item $index")
            }

            Row(modifier = Modifier.padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable{ onItemClick(items[index]) },
                verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(model = items[index].thumb,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.padding(5.dp)) {
                    Text(items[index].title)
                    Text(items[index].subtitle)
                }
            }
        }
    }
}

//
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
//@Composable
//fun MyLazyImagenPreview() {
//   MyLazyImagen(items(Type.CAT), (items(Type.CAT)[0]))
//}