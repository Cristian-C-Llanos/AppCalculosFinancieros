package com.example.appcalculosfinancieros.views.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcalculosfinancieros.views.components.CustomButton
import com.example.appcalculosfinancieros.viewmodels.CalculosEmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapturaDatosEmpleado(
    navController: NavController,
    viewModel: CalculosEmpleadoViewModel
) {
    // Variables para los campos de entrada
    var salarioBase by remember { mutableStateOf("") }
    var horasExtras by remember { mutableStateOf("") }
    var bonificaciones by remember { mutableStateOf("") }
    var deducciones by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }

    val camposLlenos = salarioBase.isNotBlank() &&
            horasExtras.isNotBlank() &&
            bonificaciones.isNotBlank() &&
            deducciones.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculos de Datos - Empleado", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF003366))
            )
        },
        bottomBar = {
            // Botón de Volver agregado aquí
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
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Campos de entrada
            CampoEntrada("Salario Base", salarioBase) { salarioBase = it }
            CampoEntrada("Horas Extras", horasExtras) { horasExtras = it }
            CampoEntrada("Bonificaciones", bonificaciones) { bonificaciones = it }
            CampoEntrada("Deducciones", deducciones) { deducciones = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Calcular
            CustomButton(
                text = "Calcular",
                onClick = {
                    if (camposLlenos) {
                        val entradas = mapOf(
                            "salarioBase" to salarioBase,
                            "horasExtras" to horasExtras,
                            "bonificaciones" to bonificaciones,
                            "deducciones" to deducciones
                        )
                        viewModel.actualizarEntradas(entradas)
                        navController.navigate("seleccion_calculo_empleado")
                    } else {
                        mostrarAlerta = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = camposLlenos
            )

            if (mostrarAlerta) {
                MostrarAlerta("Por favor, completa todos los campos.") { mostrarAlerta = false }
            }
        }
    }
}

@Composable
fun CampoEntrada(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.all { char -> char.isDigit() }) onValueChange(it) },
        label = { Text(label, fontSize = 14.sp) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun MostrarAlerta(mensaje: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("OK", color = Color(0xFF003366)) }
        },
        title = { Text("Error") },
        text = { Text(mensaje) }
    )
}
