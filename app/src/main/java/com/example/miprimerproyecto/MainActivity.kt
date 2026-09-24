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
                        Calculadora()
                    }
                }
            }
        }
    }
}

// ============================================
// EJERCICIO 8 — Calculadora pequeña, Logcat y debugger
// ============================================
// Consigna: calculadora de dos números (sumar, restar, multiplicar)
// con la lógica en una función separada. Parte B: usar breakpoint
// y Debug. Parte C: agregar un log con tag reconocible.

@Composable
fun Calculadora() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") }
        )
        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") }
        )
        Button(onClick = {
            // ?: 0.0 (operador Elvis): si toDoubleOrNull() da null
            // (campo vacío o texto inválido), usa 0.0 en vez de
            // romper la app
            val a = numero1.toDoubleOrNull() ?: 0.0
            val b = numero2.toDoubleOrNull() ?: 0.0
            resultado = calcular(a, b, "sumar").toString()
        }) {
            Text("Sumar")
        }
        Button(onClick = {
            val a = numero1.toDoubleOrNull() ?: 0.0
            val b = numero2.toDoubleOrNull() ?: 0.0
            resultado = calcular(a, b, "restar").toString()
        }) {
            Text("Restar")
        }
        Button(onClick = {
            val a = numero1.toDoubleOrNull() ?: 0.0
            val b = numero2.toDoubleOrNull() ?: 0.0
            resultado = calcular(a, b, "multiplicar").toString()
        }) {
            Text("Multiplicar")
        }
        Text("Resultado: $resultado")
    }
}

// La lógica va en una función separada de la interfaz (pide la
// consigna), NO @Composable. Acá es donde pondría el breakpoint
// para la Parte B (por ejemplo en la línea del "when"), corriendo
// con Debug en vez de Run.
fun calcular(a: Double, b: Double, operacion: String): Double {
    // Log con tag "CALCULADORA" (Parte C): se filtra por ese tag
    // en la pestaña Logcat para ver solo estos mensajes
    Log.d("CALCULADORA", "a=$a b=$b operacion=$operacion")
    return when (operacion) {
        "sumar" -> a + b
        "restar" -> a - b
        "multiplicar" -> a * b
        else -> 0.0
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Calculadora()
        }
    }
}
