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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
                        Contador()
                    }
                }
            }
        }
    }
}

@Composable
fun Contador() {
    var contador by remember { mutableStateOf(0) }

    Column {
        Text("Valor: $contador")
        Button(onClick = { contador++ }) {
            Text("+1")
        }
        Button(onClick = { if (contador > 0) contador-- }) {
            Text("-1")
        }
        Button(onClick = { contador = 0 }) {
            Text("Reiniciar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Contador()
        }
    }
}
