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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .background(Color.Red)

                    ) {
                        Text(text = "Ficha del estudiante")
                        DatoDelEstudiante(etiqueta = "Nombre", valor = "Juan")
                        DatoDelEstudiante(etiqueta = "Edad", valor = descripcionEdad(20))
                        DatoDelEstudiante(etiqueta = "Carrera", valor = "Sistemas" )
                        DatoDelEstudiante(etiqueta = "Anio", valor = "1" )
                        Botoncito()
                    }
                }
            }
        }
    }
}

@Composable
fun DatoDelEstudiante(etiqueta: String, valor: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "$etiqueta: ")
        Text(text = valor)
    }
}

fun descripcionEdad(edad: Int): String {
    if (edad >= 18) {
        return "Es mayor de edad, edad: $edad años"
    }
    return "Edad: $edad años"
}

@Composable
fun Botoncito() {
    Button(onClick = {
        println("Mi primer botoncito")
    }) {
        Text(text = "Clikea para ver la magia")
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)
            .background(Color.Red)

        ) {
            DatoDelEstudiante(etiqueta = "Nombre", valor = "Juan")
            DatoDelEstudiante(etiqueta = "Edad", valor = descripcionEdad(20))
            Botoncito()
        }
    }
}
