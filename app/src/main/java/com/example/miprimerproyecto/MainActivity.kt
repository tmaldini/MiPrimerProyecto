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
                        CompartirRecomendacion()
                    }
                }
            }
        }
    }
}
// ============================================
// EJERCICIO 12 — Primer contacto con el Android SDK: Context e Intents
// ============================================
// Consigna: pantalla "Compartir recomendación" con texto y botón.
// Al presionar, abrir el selector de apps para compartir un mensaje
// mediante Intent.ACTION_SEND.
// Parte B: otro botón que abra una dirección web mediante
// Intent.ACTION_VIEW y una URI.
//
// ------------------------------------------------------------
// KOTLIN vs COMPOSE vs ANDROID SDK — cómo distinguirlos
// ------------------------------------------------------------
// Kotlin: el lenguaje en sí (val, var, fun, if). Existiría
// aunque no estuvieras haciendo una app Android.
//
// Compose: la caja de herramientas para DIBUJAR pantallas
// (Text, Button, Column). Es una librería que se usa PARA
// Android, pero no es Android en sí.
//
// Android SDK: todo lo que tiene que ver con pedirle algo al
// SISTEMA OPERATIVO del teléfono (cámara, compartir, abrir
// el navegador). Context e Intent existían desde antes de
// que Compose existiera; no dibujan nada, le piden cosas al SO.
//
// Pregunta rápida para cada línea:
// - ¿Existiría en cualquier lenguaje? -> Kotlin
// - ¿Dibuja algo en pantalla?         -> Compose
// - ¿Le pide algo al teléfono/SO?     -> Android SDK

@Composable
fun CompartirRecomendacion() {
    // LocalContext.current es un caso mixto:
    // - la FUNCIÓN es de Compose (te la da Compose para poder
    //   acceder a cosas del SDK desde adentro de una pantalla)
    // - lo que DEVUELVE (el Context) es del Android SDK
    // Se lee AFUERA del onClick, no adentro (si lo leyeras
    // adentro, no compila: es una llamada @Composable)
    val context = LocalContext.current

    Column {
        Text(text = "Estoy aprendiendo Android con Compose")

        Button(onClick = {
            // Intent: Android SDK. Le pide al sistema que busque
            // qué apps pueden manejar la acción ACTION_SEND
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Estoy aprendiendo Android con Compose")
            }
            // createChooser: muestra el selector de apps del sistema
            context.startActivity(Intent.createChooser(intent, "Compartir con"))
        }) {
            Text("Compartir")
        }

        // Parte B: segundo botón, con ACTION_VIEW y una URI
        Button(onClick = {
            // Uri: Android SDK, representa la dirección web
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            context.startActivity(intent)
        }) {
            Text("Abrir web")
        }
    }
}

// Nota de la guía: no es necesario solicitar permisos peligrosos
// para este ejercicio. Si alguna vez una IA te sugiere agregar
// permisos en el Manifest acá, es la señal para parar y revisar
// antes de aplicarlo — no hace falta.

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
                CompartirRecomendacion()
            }
        }
    }
}

