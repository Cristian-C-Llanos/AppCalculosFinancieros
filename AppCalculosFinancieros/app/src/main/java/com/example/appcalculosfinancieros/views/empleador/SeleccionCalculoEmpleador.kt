package com.example.appcalculosfinancieros.views.empleador

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
import com.example.appcalculosfinancieros.viewmodels.CalculosEmpleadorViewModel
import com.example.appcalculosfinancieros.viewmodels.HistorialViewModel
import com.example.appcalculosfinancieros.views.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionCalculoEmpleador(
    navController: NavController,
    viewModel: CalculosEmpleadorViewModel,
    historialViewModel: HistorialViewModel
) {
    val resultadoActual by viewModel.resultado.collectAsState()
    val formulaActual by viewModel.formula.collectAsState()
    val entradas by viewModel.entradas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selección de Cálculos - Empleador", color = Color.White) },
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
                    .height(240.dp)
                    .background(Color(0xFFEEECEC))
                    .padding(8.dp)
            ) {
                Column {
                    Text("Resultados del Cálculo", fontSize = 16.sp, color = Color.Black)
                    Text("Segmento: Empleador", fontSize = 14.sp, color = Color.Gray)
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
                        text = "Calcular Aportes Parafiscales",
                        onClick = {
                            viewModel.calcularAportesParafiscales()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Aportes Parafiscales", "Empleador")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Seguridad Social",
                        onClick = {
                            viewModel.calcularSeguridadSocial()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Seguridad Social", "Empleador")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Prestaciones Sociales",
                        onClick = {
                            viewModel.calcularPrestacionesSociales()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Prestaciones Sociales", "Empleador")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
                item {
                    CustomButton(
                        text = "Calcular Costo Total de Nómina",
                        onClick = {
                            viewModel.calcularCostoTotalNomina()
                            historialViewModel.agregarCalculo(
                                viewModel.crearHistorial("Calcular Costo Total de Nómina", "Empleador")
                            )
                        },
                        modifier = Modifier.height(90.dp)
                    )
                }
            }
        }
    }
}
