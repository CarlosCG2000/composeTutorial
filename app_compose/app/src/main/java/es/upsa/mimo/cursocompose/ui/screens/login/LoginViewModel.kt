package es.upsa.mimo.cursocompose.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val loggedIn: Boolean = false,
        val error: String? = null
    )

    fun onLoginClick(user: String, pass: String){
        state = when {
            !user.contains('@') -> UiState(error = "User must be a valid email")
            pass.length < 5 -> UiState(error = "Password must be at least 5 characters")
            else -> UiState(loggedIn = true)
        }
    }

     fun onLoggedIn() {
        state = UiState(loggedIn = false)
    }

}