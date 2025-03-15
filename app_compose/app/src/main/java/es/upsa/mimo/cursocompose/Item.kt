package es.upsa.mimo.cursocompose


data class Item( val id: Int, val title: String, val subtitle: String, val thumb: String)

val items = (1.. 1000).map {
    Item (it,
        "Title $it",
        "Subtitle $it",
        "https://loremflickr.com/400/400?lock=$it"
    )
}