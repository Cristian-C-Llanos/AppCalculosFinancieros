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
    // Variables de estado para los campos de entrada
    var salarioBase by remember { mutableStateOf("") }
    var deducciones by remember { mutableStateOf("") }
    var horasDiurnas by remember { mutableStateOf("") }
    var horasNocturnas by remember { mutableStateOf("") }
    var horasFestivas by remember { mutableStateOf("") }
    var porcentajeBonificacion by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }

    // Validación de campos esenciales
    val camposValidos = salarioBase.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculos de Datos - Empleados", color = Color.White) },
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
            verticalArrangement = Arrangement.Top
        ) {
            // Campos de entrada
            CampoEntrada("Salario Base", salarioBase) { salarioBase = it }
            CampoEntrada("Deducciones", deducciones) { deducciones = it }
            CampoEntrada("Horas Extra Diurnas", horasDiurnas) { horasDiurnas = it }
            CampoEntrada("Horas Extra Nocturnas", horasNocturnas) { horasNocturnas = it }
            CampoEntrada("Horas Festivas/Dominicales", horasFestivas) { horasFestivas = it }
            CampoEntrada("% de Salario para Bonificación", porcentajeBonificacion) { porcentajeBonificacion = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Continuar
            CustomButton(
                text = "Calcular",
                onClick = {
                    val entradas = mapOf(
                        "salarioBase" to salarioBase,
                        "deducciones" to deducciones,
                        "horasDiurnas" to horasDiurnas,
                        "horasNocturnas" to horasNocturnas,
                        "horasFestivas" to horasFestivas,
                        "porcentajeBonificacion" to porcentajeBonificacion
                    )
                    viewModel.actualizarEntradas(entradas)
                    navController.navigate("seleccion_calculo_empleado")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = camposValidos
            )

            // Mostrar alerta si los campos no son válidos
            if (mostrarAlerta) {
                MostrarAlerta("Por favor, completa al menos el campo Salario Base.") {
                    mostrarAlerta = false
                }
            }
        }
    }
}

@Composable
fun CampoEntrada(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.all { char -> char.isDigit() || char == '.' }) onValueChange(it) },
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
            TextButton(onClick = onDismiss) {
                Text("OK", color = Color(0xFF003366))
            }
        },
        title = { Text("Error") },
        text = { Text(mensaje) }
    )
}
