package com.example.miprimerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
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
                    FichaDelEstudiante(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FichaDelEstudiante(modifier: Modifier = Modifier) {
    val nombre = "Ana"
    val edad = 20
    val promedio = 8.25
    val cursaProgramacion = true
    val anioProximo= edad+1
    val documento: String= "24546456"
    val materias: Int = 5
    val ciudad: String= "Cordoba"

    Column(modifier) {
        Text("Nombre $nombre", modifier = Modifier.padding(all = 30.dp) )
        Text("Edad $edad", modifier = Modifier.padding(all = 30.dp))
        Text("Promedio $promedio", modifier = Modifier.padding(all = 30.dp))
        Text("CursaProgramacion $cursaProgramacion", modifier = Modifier.padding(all = 30.dp))
        Text("AñoProximo $anioProximo", modifier = Modifier.padding(all = 30.dp))
        Text("Documento: $documento" , modifier = Modifier.padding(all = 30.dp))
//        Otra forma de mostrar los textos
        Text("Materias:" + materias, modifier = Modifier.padding(all = 30.dp))
        Text("Ciudad:" + ciudad, modifier = Modifier.padding(all = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        FichaDelEstudiante()
    }
}