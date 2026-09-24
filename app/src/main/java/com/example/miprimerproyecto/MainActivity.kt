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
            label = { Text("Número 2") }      //devuelve siempre string
        )
        Button(onClick = {
            val a = numero1.toDoubleOrNull() ?: 0.0  //lo convierte a doble o null
            val b = numero2.toDoubleOrNull() ?: 0.0
            resultado = calcular(a, b, "sumar").toString() //lo vuelve a convertir a string
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

fun calcular(a: Double, b: Double, operacion: String): Double { //no es composite porque es pura logica
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
