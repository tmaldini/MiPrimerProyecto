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

@Composable
fun ClasificadorDeEdad() {
    var nombre by remember { mutableStateOf("") }
    var edadTexto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column {
        TextField(
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
            val edadNumero = edadTexto.toIntOrNull()
            resultado = if (edadNumero == null) {
                "Error: ingresá un número válido"
            } else if (edadNumero < 0) {
                "El dato no es válido"
            } else if (edadNumero < 18) {
                "$nombre es menor de edad"
            } else {
                "$nombre es mayor de edad"
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
