package com.example.miprimerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    Presentacion(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Presentacion(modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "nombre",
            modifier = modifier.padding(all = 10.dp)
        )
        Text(
            text = "carrera",
            modifier = modifier.padding(all = 10.dp)
        )
        Text(
            text = "una tecnologia que el estudiante quiera aprender",
            modifier = modifier.padding(all = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Presentacion()
    }
}