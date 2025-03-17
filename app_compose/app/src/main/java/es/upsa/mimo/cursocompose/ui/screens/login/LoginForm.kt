package es.upsa.mimo.cursocompose.ui.screens.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import es.upsa.mimo.cursocompose.ui.theme.CursoComposeTheme

@Composable
fun LoginForm(onLogin: () -> Unit /** Para la navegación a otra vista */,
                    viewModel: LoginFormViewModel = viewModel()
) {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passVisible by rememberSaveable {  mutableStateOf(false) }
    // var error by rememberSaveable { mutableStateOf(false) } // -> EN EL VIEW MODEL
    var error = viewModel.state.error != null

    if(viewModel.state.loggedIn){
        onLogin()
        viewModel.onLoggedIn() // Si ya esta in se quite a false y no entre de manera repetida aqui
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = user,
            onValueChange = { user = it },
            label = {Text("User")},
            placeholder = {Text("Write your email")},
            isError = error
        )

        TextField(value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Write your password") },
            isError = error,
            trailingIcon = {
                PassVisibleIcon (passVisible,
                    { passVisible = it })
            },
            visualTransformation = if(passVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )

        Button(onClick = {
            viewModel.onLoginClick(user, password)
            // user = ""
            // password = ""
//            var userError = !user.contains("@")
//            val passError = password.length < 5
//            error = userError || passError
//
//            if(!error){
//                onLogin()
//            }
        },
            enabled = (user.isNotEmpty() && password.isNotEmpty())
        ){ Text("Registrar")}

        viewModel.state.error?.let { error ->
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
private fun PassVisibleIcon(visible: Boolean, onVisibleChange:(Boolean) -> Unit): Boolean {

    IconToggleButton(
        checked = visible,
        onCheckedChange = { onVisibleChange(it) }
    ) {
        Icon(
            imageVector = if (visible) {
                Icons.Default.Visibility
            } else {
                Icons.Default.VisibilityOff
            },
            contentDescription = null
        )
    }
    return visible
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
// @Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Modo Oscuro")
@Composable
fun Ejer4ExtraLoginPreview() {
    CursoComposeTheme {
        LoginForm({})
    }
}
