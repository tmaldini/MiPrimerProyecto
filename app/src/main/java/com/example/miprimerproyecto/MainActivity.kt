package com.example.miprimerproyecto

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Surface
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

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
                        OrganizadorDeCursada()
                    }
                }
            }
        }
    }
}
// ============================================
// EJERCICIO 13 — Miniaplicación integradora: organizador de cursada
// ============================================
// Consigna: "Organizador de cursada" con actividades académicas.
// Debe permitir: ingresar título, ingresar/seleccionar materia,
// asignar prioridad, agregar la actividad, listar con LazyColumn,
// marcar como completada, eliminar, mostrar cuántas pendientes.

data class Actividad(
    val titulo: String,
    val materia: String,
    val prioridad: Int,
    val completada: Boolean = false
)

@Composable
fun OrganizadorDeCursada() {
    var titulo by remember { mutableStateOf("") }
    var materia by remember { mutableStateOf("") }
    var prioridadTexto by remember { mutableStateOf("") }

    // mutableStateListOf, mismo motivo que en el Ejercicio 10
    val actividades = remember { mutableStateListOf<Actividad>() }

    Column {
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") }
        )
        OutlinedTextField(
            value = materia,
            onValueChange = { materia = it },
            label = { Text("Materia") }
        )
        OutlinedTextField(
            value = prioridadTexto,
            onValueChange = { prioridadTexto = it },
            label = { Text("Prioridad (número)") }
        )

        Button(onClick = {
            // mismo patrón de validación que el Ejercicio 10
            val prioridad = prioridadTexto.toIntOrNull()
            if (titulo.isNotBlank() && materia.isNotBlank() && prioridad != null) {
                actividades.add(Actividad(titulo, materia, prioridad))
                titulo = ""
                materia = ""
                prioridadTexto = ""
            }
        }) {
            Text("Agregar actividad")
        }

        // Requisito: mostrar cuántas están pendientes.
        // count { !it.completada }, no it.completada (eso contaría
        // las YA completadas, es el error típico de este ejercicio)
        Text("Pendientes: ${actividades.count { !it.completada }}")

        // items(), no itemsIndexed: mismo patrón que Ejercicio 9 y 10
        LazyColumn {
            items(actividades) { actividad ->
                Column {
                    Row {
                        Text(text = actividad.titulo)
                        Text(text = " - ${actividad.materia}")
                        Text(text = " - Prioridad ${actividad.prioridad}")
                    }
                    Text(text = if (actividad.completada) "Completada" else "Pendiente")

                    Row {
                        Button(onClick = {
                            val index = actividades.indexOf(actividad)
                            actividades[index] = actividad.copy(completada = true)
                        }) {
                            Text("Completar")
                        }
                        Button(onClick = {
                            actividades.remove(actividad)
                        }) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichaPreview() {
    MiPrimerProyectoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OrganizadorDeCursada()
            }
        }
    }
}

