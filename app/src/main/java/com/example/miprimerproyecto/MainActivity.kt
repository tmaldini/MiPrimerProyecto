package com.example.miprimerproyecto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color

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
                        ListaDeTareas()
                    }
                }
            }
        }
    }
}

// ============================================
// EJERCICIO 10 — Lista editable: tareas del estudiante
// ============================================
// Consigna: campo para escribir una tarea, botón Agregar, lista
// de tareas, posibilidad de eliminar una tarea, no agregar
// cadenas vacías.
// (Los desafíos —cantidad, Borrar todas, marcar completada—
// NO están acá: la consigna base no los pide.)

@Composable
fun ListaDeTareas() {
    var texto by remember { mutableStateOf("") }

    // mutableStateListOf, no mutableListOf ni listOf: necesito
    // que la colección sea observable por Compose, porque se
    // agregan y sacan elementos en tiempo de ejecución (a
    // diferencia del Ejercicio 9, donde la lista era fija)
    val tareas = remember { mutableStateListOf<String>() }

    Column {
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Nueva tarea") }
        )
        Button(onClick = {
            // isNotBlank(), no isNotEmpty(): "no agregar cadenas
            // vacías" también debería descartar texto con solo
            // espacios, no solo texto de longitud 0
            if (texto.isNotEmpty()) {
                tareas.add(texto)
                texto = ""
            }
        }) {
            Text("Agregar")
        }

        LazyColumn {
            items(tareas) { tarea ->
                Row {
                    Text(text = tarea)
                    Button(onClick = { tareas.remove(tarea) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            ListaDeTareas()
        }
    }
}
