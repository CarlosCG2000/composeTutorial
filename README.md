
# Jetpack Compose

____________________ PARTE 1 ____________________

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

* 1. Ejercicio: Crea un botón que muestre un mensaje pasado por argumento al Composable, y que muestre un Toast al hacer click.

### 1.2 @Preview
Para tener laapariencia de nuestro componente
Se pueden crear varios previews.

## 2. Layouts
La importancia del posicionamiento de componentes.

### 2.1 Box
### 2.2 Columm
### 2.3 Row

* 2. Ejercicio: Crea una columna con 2 TextFields y un botón.

IMAGEN [`1. Compose vs XML`]

## 3. Modifier
Nos permite añadir comportamiento a los elementos de nuestra UI.

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
• remember y mutableStateOf trabajan juntos
• Hay varias formas de usar remember en función de la situación

* 3 Ejercicio: Crea un state para cada uno de los TextFields. Activa el botón solo si los dos campos tienen datos, y cuando se haga click, muestra un Toast y borra los campos.

* 4 Ejercicio: Mejorar el ejer 3 añadiendo un icono y la comprobación de errores en el formulario.
Dependencia de extensión de iconos: buscar `material-icons` obtener `material-icons-extended`.
Fichero `LoginForm.kt`

## 5. Layouts dinámicos.
* 5 Ejercicio: Crea un LazyColumn que pinte la vista anterior usando estos items
```java
data class Item( val id: Int, val title: String, val subtitle: String, val thumb: String)

val items = (1.. 1000).map {
    Item (it,
        "Title $it",
        "Subtitle $it",
        "https://loremflickr.com/400/400?lock=$it"
    )
}
```

Creamos un fichero `Item.kt`

## 6. Carga de imágenes con Coil
Librería externa: COIL poniendo `io.coil-kt` y aplicamos la de `coil-compose`.

Y en el android Manifest añadimos: `<uses-permission android:name="android.permission.INTERNET" />`

Creamos un nuevo fichero `ItemList.kt`

## 7. Scaffold
La barra como la Toolbar.

Creamos un nuevo fichero `ItemListScaffold.kt`

## 8. Navegación
Añadir la navegación como dependencias: `navigation-compose` implementamos `navigation-compose`

Si estamos en una jerarquía donde todo sea Compose, podemos usar la librería `Navigation-Compose`.

Fichero `Navegations.kt`

## 9. Temas
- Colores: un esquema de colores con los colores principales y luego con un esquema de colores
- Tipografías
- Formas

____________________ PARTE 2 ____________________

## 10. Dependencias BOM (`ya por defecto`)
Lo que nos permite es gestionar la dependecias de varias librerias que trabajan en común de forma conjunta de forma que ese `BOM`, siempre nos va a devolver las versiones de las dependecias que pueden trabajar entre ellas sin conflictos.

## 11. Kotlin DSL (`ya por defecto`)
Pasar los archivos del Gradle (en rubi) (`settings.gradle`) a Kotlin Scripts (en kotlin) (`setting.gradle.kts`)

## 12. Version Catalog (`ya por defecto`)
Es el `libs.versions.toml`

## 13. TopAppBar
Pasamos la `TopAppBar` que teniamos en `ItemListScaffold.kt` a un nuevo archivo `MyTopAppBar.kt`.

## 14. Bottom Navigation
La barra de navegación inferior.

Se realiza en el fichero `ItemListScaffold.kt` dentro del `Scaffold`, la función `BottomNavigation`.
Tambien se va a tener que editar el `Item.kt` convirtiendolo a función en vez de variable.

## 15. SnackBar
Es una barra en la parte inferior indicando una información temporal (equivalente a los antiguos Toast).
