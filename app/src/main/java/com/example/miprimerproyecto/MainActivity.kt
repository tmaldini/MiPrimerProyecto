package com.example.miprimerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miprimerproyecto.ui.theme.MiPrimerProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)) {
                        ClasificadorDeEdad()
                    }
                }
            }
        }
    }
}

// ============================================
// EJERCICIO 7 — TextField, if/when y null safety
// ============================================
// Consigna: "Clasificador de edad" con campo nombre, campo edad,
// botón Evaluar y resultado. Manejar texto no numérico, negativo,
// menor y mayor de edad.
// Desafío: reemplazar el if por when.

@Composable
fun ClasificadorDeEdad() {
    var nombre by remember { mutableStateOf("") }
    var edadTexto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = edadTexto,
            onValueChange = { edadTexto = it },
            label = { Text("Edad") }
        )
        Button(onClick = {
            // toIntOrNull() en vez de toInt(): si el texto no es
            // un número válido, devuelve null en vez de cerrar la app
            val edadNumero = edadTexto.toIntOrNull()

            // when sin argumento (reemplaza la cadena de if/else):
            // el chequeo de null va SIEMPRE primero. Recién después
            // de descartarlo, Kotlin sabe que edadNumero es un Int
            // seguro (smart cast) y puede compararlo con < 0 y < 18
            resultado = when {
                edadNumero == null -> "Error: ingresá un número válido"
                edadNumero < 0 -> "El dato no es válido"
                edadNumero < 18 -> "$nombre es menor de edad"
                else -> "$nombre es mayor de edad"
            }
        }) {
            Text("Evaluar")
        }
        Text(resultado)
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            ClasificadorDeEdad()
        }
    }
}
