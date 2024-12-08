package com.example.appcalculosfinancieros.views.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcalculosfinancieros.viewmodels.CalculosProductosViewModel
import com.example.appcalculosfinancieros.viewmodels.HistorialViewModel
import com.example.appcalculosfinancieros.views.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionCalculoProducto(
    navController: NavController,
    viewModel: CalculosProductosViewModel,
    historialViewModel: HistorialViewModel
) {
    val resultadoActual by viewModel.resultado.collectAsState()
    val formulaActual by viewModel.formula.collectAsState()
    val entradas by viewModel.entradas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selección de Cálculos - Productos", color = Color.White) },
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp) // Aumentado un 50%
                    .background(Color(0xFFEEECEC))
                    .padding(8.dp)
            ) {
                Column {
                    Text("Resultados del Cálculo", fontSize = 16.sp, color = Color.Black)
                    Text("Segmento: Productos", fontSize = 14.sp, color = Color.Gray)
                    entradas.forEach { (clave, valor) ->
                        Text("$clave: $valor", fontSize = 14.sp, color = Color.Gray)
                    }
                    Text("Fórmula: $formulaActual", fontSize = 14.sp, color = Color.Gray)
                    Text("Resultado: $resultadoActual", fontSize = 14.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    CustomButton(
                        text = "Calcular Precio con IVA",
                        onClick = {
                            viewModel.calcularPrecioConIVA()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Precio con IVA", "Productos")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Margen de Ganancia",
                        onClick = {
                            viewModel.calcularMargenDeGanancia()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Margen de Ganancia", "Productos")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Punto de Equilibrio",
                        onClick = {
                            viewModel.calcularPuntoDeEquilibrio()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Punto de Equilibrio", "Productos")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular ROI",
                        onClick = {
                            viewModel.calcularROI()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular ROI", "Productos")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
            }
        }
    }
}
