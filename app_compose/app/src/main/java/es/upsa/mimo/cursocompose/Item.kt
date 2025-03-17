package es.upsa.mimo.cursocompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.EmojiNature
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.ui.graphics.vector.ImageVector


data class Item( val id: Int, val title: String, val subtitle: String, val thumb: String)

enum class Type(val title: String, val icon: ImageVector){
    CAT( "Cat", Icons.Default.CrueltyFree),
    DOG( "Dog", Icons.Default.Pets),
    HORSE("Horse", Icons.Default.BugReport),
    COW("Cow", Icons.Default.EmojiNature)
}

fun items(type: Type) = (1.. 1000).map {
    Item (it,
        "${type.title} $it",
        "Subtitle $it",
        "https://loremflickr.com/400/400?lock=$it"
    )
}