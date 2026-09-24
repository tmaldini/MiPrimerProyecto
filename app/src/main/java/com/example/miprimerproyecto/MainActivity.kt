package com.example.miprimerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miprimerproyecto.ui.theme.MiPrimerProyectoTheme

// ============================================
// EJERCICIO 5 — Column, Row, Modifier y Material 3
// ============================================
// Consigna: rediseñar la ficha con título, datos en vertical,
// al menos una fila con dos elementos, espaciado con padding
// y un Button sin comportamiento relevante.
// Criterio: usar Column, Row, al menos 3 modificadores
// y un componente Material 3 además de Text.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerProyectoTheme {
                // Scaffold: estructura base de Material 3 (acá no usa topBar
                // ni FAB, pero da el innerPadding para no tapar contenido
                // con las barras del sistema)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)   // Modificador 1: respeta el espacio del Scaffold
                            .padding(16.dp)           // Modificador 2: espaciado exterior que pide la consigna
                            .background(Color.Red)    // Modificador 3: fondo, para el experimento del orden
                        // (probé cambiar el orden de padding y background:
                        // sí afecta el resultado. Con padding primero, el
                        // fondo rojo queda "adentro" del margen; si el
                        // background va primero, el rojo llega hasta el borde
                        // y el padding queda como aire encima del rojo)
                    ) {
                        // Título que pide la consigna
                        Text(text = "Ficha del estudiante")

                        // Datos organizados verticalmente (uno por línea,
                        // porque están dentro de la Column exterior)
                        DatoDelEstudiante(etiqueta = "Nombre", valor = "Juan")
                        DatoDelEstudiante(etiqueta = "Edad", valor = descripcionEdad(20))
                        DatoDelEstudiante(etiqueta = "Carrera", valor = "Sistemas")
                        DatoDelEstudiante(etiqueta = "Anio", valor = "1")

                        // Componente Material 3 adicional a Text, sin comportamiento relevante
                        Botoncito()
                    }
                }
            }
        }
    }
}

// Composable reutilizable: cada "dato" se muestra en una Row
// (etiqueta + valor, uno al lado del otro). Esto es lo que cumple
// el punto "al menos una fila con dos elementos" — no hizo falta
// agregar una Row aparte, ya está resuelto acá.
@Composable
fun DatoDelEstudiante(etiqueta: String, valor: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "$etiqueta: ")
        Text(text = valor)
    }
}

// Función Kotlin común, NO @Composable, porque no dibuja UI,
// solo calcula y devuelve un String. Separar la lógica de la
// interfaz es buena práctica (mismo criterio que en el Ejercicio 8).
fun descripcionEdad(edad: Int): String {
    if (edad >= 18) {
        return "Es mayor de edad, edad: $edad años"
    }
    return "Edad: $edad años"
}

// Button "todavía sin comportamiento relevante": el println
// no cuenta como funcionalidad real, cumple lo que pide la consigna.
@Composable
fun Botoncito() {
    Button(onClick = {
        println("Mi primer botoncito")
    }) {
        Text(text = "Clikea para ver la magia")
    }
}

// Preview: muestra lo mismo que MainActivity, pero sin correr el
// emulador. OJO: es una Column DISTINTA a la de MainActivity, por
// eso tiene su propio Modifier repetido acá (si cambio uno,
// tengo que cambiar el otro a mano, no se comparten).
@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Red)
        ) {
            DatoDelEstudiante(etiqueta = "Nombre", valor = "Juan")
            DatoDelEstudiante(etiqueta = "Edad", valor = descripcionEdad(20))
            Botoncito()
        }
    }
}