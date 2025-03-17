package es.upsa.mimo.cursocompose.ui.screens.itemList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ItemListViewModel: ViewModel() {

    var state by mutableStateOf(UiState())// el VM sobrevive a los a los cambios de configuraciones, por eso no necesita el remeber. S epuede usar tb un 'MutableStateFlow'
        private set // para que no se puede modificar desde fuera

    // Estado de elementos que se va a pintar
    data class UiState(
        val selectType: Type = Type.CAT,
        val items: List<Item> = items(selectType)
    )

    // Para cuando se cambie el tipo, se haga clic en la barra inferior de la pantalla
    fun onTypeChange(type: Type){
        state = UiState(type)
    }


}