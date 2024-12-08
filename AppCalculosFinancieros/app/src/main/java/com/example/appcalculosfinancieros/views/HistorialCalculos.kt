package com.example.appcalculosfinancieros.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcalculosfinancieros.viewmodels.HistorialViewModel
import com.example.appcalculosfinancieros.views.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialCalculos(navController: NavController, viewModel: HistorialViewModel) {
    val historial by viewModel.historial.collectAsState()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Historial de Cálculos",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF003366))
            )
        },
        bottomBar = {
            CustomButton(
                text = "Volver",
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            if (historial.isEmpty()) {
                Text(
                    text = "No hay cálculos disponibles.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            } else {
                LazyColumn {
                    // Invertir la lista para mostrar los cálculos más recientes primero
                    items(historial.reversed()) { calculo ->
                        HistorialItem(
                            nombre = calculo.nombre,
                            entradas = calculo.entradas,
                            formula = calculo.formula,
                            resultado = calculo.resultado
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun HistorialItem(
    nombre: String,
    entradas: Map<String, String>,
    formula: String,
    resultado: String
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Cálculo: $nombre", fontSize = 16.sp, color = Color.Black)
            Text("Segmento: Productos", fontSize = 14.sp, color = Color.Gray)
            if (entradas.isNotEmpty()) {
                entradas.forEach { (clave, valor) ->
                    Text("$clave: $valor", fontSize = 14.sp, color = Color.Gray)
                }
            } else {
                Text("Sin datos de entrada", fontSize = 14.sp, color = Color.Red)
            }
            Text("Fórmula: $formula", fontSize = 14.sp, color = Color.Gray)
            Text("Resultado: $resultado", fontSize = 14.sp, color = Color.Black)
        }
    }
}
