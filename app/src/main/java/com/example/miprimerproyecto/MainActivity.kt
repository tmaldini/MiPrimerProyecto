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
                        ListaDeMaterias()
                    }
                }
            }
        }
    }
}

data class Materia(  //clase que no modifica sus valores
    val nombre: String,
    val anio: Int,
    val aprobada: Boolean
)

@Composable
fun ListaDeMaterias() {
    val materias = listOf( //lista no mutable
        Materia("Programacion I", 1, true),
        Materia("Matematica", 1, false),
        Materia("Programacion II", 1, true),
        Materia("Fisica", 2, false),
        Materia("Base de Datos", 2, true)
    )

    Column {
        Text("Cantidad de materias: ${materias.size}") //muestra la cantidad de materias
        LazyColumn {  //lista que muestra solo lo visible no como column que dibujaria las 5 materias
//            items(materias.filter { it.aprobada }) { materia -> para ver solo las aprobadas es decir true
            items(materias) { materia ->
                MateriaItem(materia)
            }
        }
    }
}

@Composable
fun MateriaItem(materia: Materia) {
    Row {
        Text(text = materia.nombre)
        Text(text = " - Año ${materia.anio}")
        Text(text = if (materia.aprobada) " - Aprobada" else " - Pendiente") //cambio dinamico si esta aprobado o no
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionPreview() {
    MiPrimerProyectoTheme {
        Column(modifier = Modifier
            .padding(16.dp)) {
            ListaDeMaterias()
        }
    }
}
