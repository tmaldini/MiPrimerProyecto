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
                        ListaDeMaterias()
                    }
                }
            }
        }
    }
}

// ============================================
// EJERCICIO 9 — data class, List y LazyColumn
// ============================================
// Consigna: modelar una materia universitaria, mostrar al menos
// 5 en una LazyColumn, con un composable propio por fila.
// Desafíos: filtrar aprobadas, mostrar cantidad total, cambiar
// visualmente el texto según el estado.

// import androidx.compose.foundation.lazy.items
// 👆 OJO: este import es obligatorio. Sin él, "items" resuelve
// contra la versión que espera un Int (cantidad) en vez de una
// List<Materia>, y tira "Argument type mismatch".

data class Materia(
    val nombre: String,
    val anio: Int,
    val aprobada: Boolean
)

@Composable
fun ListaDeMaterias() {
    val materias = listOf(
        Materia("Programacion I", 1, true),
        Materia("Matematica", 1, false),
        Materia("Programacion II", 1, true),
        Materia("Fisica", 2, false),
        Materia("Base de Datos", 2, true)
    )

    Column {
        // Desafío 2: cantidad total con .size (sin paréntesis,
        // es una propiedad, no una función)
        Text("Cantidad de materias: ${materias.size}")

        // LazyColumn: solo dibuja lo que está visible en pantalla
        // (a diferencia de Column, que dibujaría las 5 materias
        // de una aunque no se vean todas)
        LazyColumn {
            // Desafío 1: filter devuelve una lista NUEVA solo con
            // las materias donde aprobada == true
            items(materias.filter { it.aprobada }) { materia ->
                MateriaItem(materia)
            }
        }
    }
}

// Composable independiente para una sola fila (pide la consigna).
// Recibe una Materia y muestra sus datos.
@Composable
fun MateriaItem(materia: Materia) {
    Row {
        Text(text = materia.nombre)
        Text(text = " - Año ${materia.anio}")
        // Desafío 3: el texto (y acá también el color) cambia
        // según el estado de "aprobada"
        Text(
            text = if (materia.aprobada) " - Aprobada" else " - Pendiente",
            color = if (materia.aprobada) Color.Green else Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            ListaDeMaterias()
        }
    }
}
