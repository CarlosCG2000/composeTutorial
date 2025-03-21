
# `JETPACK COMPOSE` POR `ANTONIO LEIVA`

# _______ PARTE 1 _______

## ¿Qué es?
• Sistema de una interfaz declarativa a través de funciones.
• Es reactivo.
• Sustituye XMLs pero es interoperable.
• Solo se puede usar Kotlin y no en Java

## Vamos al proyecto

## 1 Elementos básicos

### 1.1 @Composable
Todas las funciones que no emitan valores (retornen valor) deben iniciarse en mayúscula porque se entienden que van a mostrar una interfaz (UI).
Poniendo en letras `comp` + pulsado `enter` se puede añadir directamente una función composable.

Una función `@Composable` solo se puede llamar dentro de otro `@Composable`.

* 1. Ejercicio: Crea un botón que muestre un mensaje pasado por argumento al Composable, y que muestre un Toast al hacer click. [Resultado: `MyButton(message: String)` en el `MainActivity.kt`]

### 1.2 @Preview
Para tener la apariencia de nuestro componente. Se pueden crear varios previews.

## 2. Layouts
La importancia del posicionamiento de componentes.

### 2.1 Box
[Ejemplo `MyBox()` en el `MainActivity.kt`]

### 2.2 Columm
[Ejemplo `MyColumn()` en el `MainActivity.kt`]

### 2.3 Row
* 2. Ejercicio: Crea una columna con 2 TextFields y un botón. [Resultado: `SimpleLogin()` en el `MainActivity.kt`]

IMAGEN [`1. Compose vs XML`]

## 3. Modifier
Nos permite añadir comportamiento a los elementos de nuestra UI.

[Ejemplo `MyTextButton()` en el `MainActivity.kt`]

• Decora o añade comportamiento a los elementos de UI
• Formato builder
• El orden es importante
• Los scopes le añaden funciones extra

IMAGEN [`2. Tipos Modifiers`]

## 4. Estado
• La UI se actualiza automáticamente cuando el estado cambia
• Utilizar el objeto `State`
• Sobrevivir a recomposiciones
• Sobrevivir a rotaciones

Tenemos dos funciones: `remember` y `mutableStateOf`
• `remember` y `mutableStateOf` trabajan juntos
• Hay varias formas de usar `remember` en función de la situación

[Ejemplo `MyLoginState()` en el `MainActivity.kt`]

* 3 Ejercicio: Crea un state para cada uno de los TextFields. Activa el botón solo si los dos campos tienen datos, y cuando se haga click, muestra un Toast y borra los campos. [Resultado: `MyLoginEnabled()` en el `MainActivity.kt`]

* 4 Ejercicio: Mejorar el `ejercicio 3` añadiendo un icono y la comprobación de errores en el formulario.
[Resultado:
- `LoginForm(onLogin: () -> Unit, viewModel: LoginFormViewModel = viewModel())` en el fichero `screens/login/LoginForm.kt`
- `PassVisibleIcon` en el fichero `screens/login/component/PassVisibleIcon.kt`
- `LoginFormViewModel: ViewModel()` en el fichero `screens/login/LoginFormViewModel.kt`]
- `LoginFormUiState` en el fichero `screens/login/LoginFormUiState.kt`

Dependencia de extensión de iconos: buscar `material-icons` obtener `material-icons-extended`.
Fichero `LoginForm.kt`

## 5. Layouts dinámicos.
[Ejemplo `MyLazyColumn()` en el `MainActivity.kt`]

* 5 Ejercicio: Crea un LazyColumn que pinte la vista anterior usando estos items.
[Creamos un fichero `Item.kt` en el `screens/itemList/Item.kt`]
```java
data class Item( val id: Int, val title: String, val subtitle: String, val thumb: String )

val items = (1.. 1000).map {
    Item (it,
        "Title $it",
        "Subtitle $it",
        "https://loremflickr.com/400/400?lock=$it"
    )
}
```
[Resultado `SimpleLazyColumn()` en el `MainActivity.kt`]

## 6. Carga de imágenes con Coil
Librería externa: COIL poniendo `io.coil-kt` y aplicamos la de `coil-compose`.

Y en el android Manifest añadimos: `<uses-permission android:name="android.permission.INTERNET" />`

[Ejemplo `ItemList()` en el `screens/itemList/components/ItemList.kt`]

## 7. Scaffold
La barra como la Toolbar.

[Ejemplo `ItemListComplete` en el `screens/itemList/ItemListComplete.kt`]

## 8. Navegación
Añadir la navegación como dependencias: `navigation-compose` implementamos `navigation-compose`

Si estamos en una jerarquía donde todo sea Compose, podemos usar la librería `Navigation-Compose`.

[Fichero `Navegations.kt` ]

## 9. Temas
- Colores: un esquema de colores con los colores principales y luego con un esquema de colores
- Tipografías
- Formas

# _______ PARTE 2 _______

## 10. Dependencias BOM (`ya por defecto`)
Lo que nos permite es gestionar la dependecias de varias librerias que trabajan en común de forma conjunta de forma que ese `BOM`, siempre nos va a devolver las versiones de las dependecias que pueden trabajar entre ellas sin conflictos.

## 11. Kotlin DSL (`ya por defecto`)
Pasar los archivos del Gradle (en rubi) (`settings.gradle`) a Kotlin Scripts (en kotlin) (`setting.gradle.kts`)

## 12. Version Catalog (`ya por defecto`)
Es el `libs.versions.toml`

## 13. TopAppBar
Pasamos la `TopAppBar` que teniamos en `ItemListComplete.kt` a un nuevo archivo `MyTopAppBar.kt`.

[Ejemplo` MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior?, onNavigationClick:() -> Unit)` en el `screens/itemList/components/MyTopAppBar.kt`]

## 14. Bottom Navigation
La barra de navegación inferior.

Se realiza en el fichero `ItemListComplete.kt` dentro del `Scaffold`, la función `BottomNavigation`.
Tambien se va a tener que editar el `Item.kt` convirtiendolo a función en vez de variable.

[Ejemplo `MyBottomNavigationBar(selectedType: Type, onTypeClick: (Type) -> Unit)` en el `screens/itemList/components/MyBottomNavigationBar.kt`]

## 15. SnackBar
Es una barra en la parte inferior indicando una información temporal (equivalente a los antiguos Toast).

Se realiza en el fichero `ItemListComplete.kt` dentro del `Scaffold`, la función `onItemClick` en el listado.

Tambien se va a tener que editar el `ItemList.kt` pasando por parámetro en la lambda para la acción.

## 16. Navigation Drawer
Como hacer que aparezca elmenu lateral.

Añadimos el `ModalNavigationDrawer` en el fichero `ItemListComplete.kt` y en mi `MyTopAppBar` la funcionalidad del click al icono del menú.

## 17. ViewModel
Añadimos la dependecia: buscamos `androidx.lifecycle` y seleccionamos `lifecycle-viewmodel-compose`

Lo hemos dividido en carpetas separadas entre la vista del `Listado`, y del `Login` cada una con su correspondiente `lógica` pasada de la `UI` a su correspondiente `View Model`.

