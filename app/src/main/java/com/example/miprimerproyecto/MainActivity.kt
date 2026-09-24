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

// ============================================
// EJERCICIO 6 — Estado, botones y recomposición: contador
// ============================================
// Consigna: contador con texto de valor actual, botón +1,
// botón -1, botón Reiniciar, y que nunca baje de cero.
// Criterio: los tres botones funcionan y el valor nunca es negativo.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)  // respeta el espacio del Scaffold
                            .padding(16.dp)          // espaciado exterior
                    ) {
                        Contador()
                    }
                }
            }
        }
    }
}

@Composable
fun Contador() {
    // Estado del contador:
    // - var: porque el valor cambia
    // - remember: para que NO se reinicie en cada recomposición
    // - mutableStateOf: para que sea observable por Compose
    //   (sin esto, Compose no se entera cuando cambia y la
    //   pantalla no se actualiza sola)
    var contador by remember { mutableStateOf(0) }

    // Column porque son 4 elementos (texto + 3 botones); sin
    // layout se superpondrían todos en el mismo lugar
    Column {
        // Este Text depende de "contador": cada vez que el estado
        // cambia, Compose recompone SOLO esta parte, no toda la Activity
        Text("Valor: $contador")

        // Botón +1: no necesita validación, sumar nunca baja de 0
        Button(onClick = { contador++ }) {
            Text("+1")
        }

        // Botón -1: ACÁ está el punto clave del ejercicio.
        // El "if (contador > 0)" es la validación que pide la
        // consigna: sin esto, el contador podría llegar a -1, -2...
        // Ojo: tiene que ser "> 0", no ">= 0" — con ">= 0" dejaría
        // restar estando ya en 0, y llegaría a -1 igual.
        Button(onClick = { if (contador > 0) contador-- }) {
            Text("-1")
        }

        // Botón Reiniciar: vuelve el estado a su valor inicial
        Button(onClick = { contador = 0 }) {
            Text("Reiniciar")
        }
    }
}

// Preview: acá SÍ coincide con lo que se ve en MainActivity,
// porque Contador() no tiene título ni nada más que mostrar
// (a diferencia del Ejercicio 5, donde el Preview y la Activity
// podían desincronizarse)
@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Contador()
        }
    }
}