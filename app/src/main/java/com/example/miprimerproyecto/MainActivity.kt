package com.example.miprimerproyecto

import android.content.res.Configuration
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
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
                        ListaDeTareas()
                    }
                }
            }
        }
    }
}
// ============================================
// EJERCICIO 11 — Recursos Android y configuración visual
// ============================================
// Consigna: mover los textos importantes a strings.xml y
// usarlos desde Compose con stringResource. Crear al menos
// dos previews: uno normal y otro en modo oscuro (u otra
// configuración visual disponible).



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
            Text(text = stringResource(R.string.agregar))
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

// Preview 1: configuración normal
@Preview(showBackground = true)
@Composable
fun FichaPreview() {
    MiPrimerProyectoTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Text(text = stringResource(R.string.titulo_ficha))
        }
    }
}

// Preview 2: modo oscuro
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FichaPreviewOscuro() {
    MiPrimerProyectoTheme(darkTheme = true, dynamicColor = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Text(text = stringResource(R.string.titulo_ficha))
        }
    }
}

