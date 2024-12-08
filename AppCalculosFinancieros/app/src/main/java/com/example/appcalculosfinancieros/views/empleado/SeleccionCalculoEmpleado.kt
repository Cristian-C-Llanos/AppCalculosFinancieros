package com.example.appcalculosfinancieros.views.empleado

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
import com.example.appcalculosfinancieros.viewmodels.CalculosEmpleadoViewModel
import com.example.appcalculosfinancieros.viewmodels.HistorialViewModel
import com.example.appcalculosfinancieros.views.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionCalculoEmpleado(
    navController: NavController,
    viewModel: CalculosEmpleadoViewModel,
    historialViewModel: HistorialViewModel
) {
    val resultadoActual by viewModel.resultado.collectAsState()
    val formulaActual by viewModel.formula.collectAsState()
    val entradas by viewModel.entradas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selección de Cálculos - Empleado", color = Color.White) },
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
                    .height(240.dp) // Espacio aumentado para resultados
                    .background(Color(0xFFEEECEC))
                    .padding(8.dp)
            ) {
                Column {
                    Text("Resultados del Cálculo", fontSize = 16.sp, color = Color.Black)
                    Text("Segmento: Empleado", fontSize = 14.sp, color = Color.Gray)
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
                        text = "Calcular Salario Neto",
                        onClick = {
                            viewModel.calcularSalarioNeto()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Salario Neto", "Empleado")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Hora Extra Diurna",
                        onClick = {
                            viewModel.calcularHoraExtraDiurna()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Hora Extra Diurna", "Empleado")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Hora Extra Nocturna",
                        onClick = {
                            viewModel.calcularHoraExtraNocturna()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Hora Extra Nocturna", "Empleado")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Hora Dominical/Festiva",
                        onClick = {
                            viewModel.calcularHoraDominicalFestiva()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Hora Dominical/Festiva", "Empleado")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
            }
        }
    }
}
