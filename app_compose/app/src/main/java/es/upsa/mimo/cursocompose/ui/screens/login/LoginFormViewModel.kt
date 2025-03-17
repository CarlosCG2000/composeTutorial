package es.upsa.mimo.cursocompose.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginFormViewModel: ViewModel() {

    var state by mutableStateOf(LoginFormUiState())
        private set // no se puede modificar la varaible fuera de aquí

    fun onLoginClick(user: String, pass: String){
        state = when {
            !user.contains('@') -> LoginFormUiState(error = "User must be a valid email")
            pass.length < 5 -> LoginFormUiState(error = "Password must be at least 5 characters")
            else -> LoginFormUiState(loggedIn = true)
        }
    }

     fun onLoggedIn() {
        state = LoginFormUiState(loggedIn = false)
    }

}