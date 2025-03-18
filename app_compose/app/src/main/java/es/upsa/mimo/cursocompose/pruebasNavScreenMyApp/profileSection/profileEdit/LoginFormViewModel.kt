package es.upsa.mimo.cursocompose.pruebasNavScreenMyApp.profileSection.profileEdit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileFormViewModel: ViewModel() {

    var state by mutableStateOf(ProfileFormUiState())
        private set // no se puede modificar la varaible fuera de aquí

    fun onLoginClick(user: String, pass: String){
        state = when {
            user.contains('@') -> ProfileFormUiState(error = "User must be a valid name")
            pass.length < 5 -> ProfileFormUiState(error = "Password must be at least 5 characters")
            else -> ProfileFormUiState(loggedIn = true)
        }
    }

     fun onLoggedIn() {
        state = ProfileFormUiState(loggedIn = false)
    }

}