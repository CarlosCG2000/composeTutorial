package es.upsa.mimo.cursocompose.ui.screens.itemList

// Estado de elementos que se va a pintar
data class ItemListUiState(
    val selectType: Type = Type.CAT,
    val items: List<Item> = items(selectType)
)

