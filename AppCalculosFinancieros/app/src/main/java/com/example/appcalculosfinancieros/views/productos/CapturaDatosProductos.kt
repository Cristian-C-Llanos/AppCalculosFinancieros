package com.example.appcalculosfinancieros.views.productos

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
import com.example.appcalculosfinancieros.viewmodels.CalculosProductosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapturaDatosProductos(
    navController: NavController,
    viewModel: CalculosProductosViewModel // Se recibe el ViewModel correctamente
) {
    // Variables para cada campo de entrada
    var precioBase by remember { mutableStateOf("") }
    var costoProducto by remember { mutableStateOf("") }
    var costosFijos by remember { mutableStateOf("") }
    var costoVariable by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }

    // Validación de campos llenos
    val camposLlenos = precioBase.isNotBlank() &&
            costoProducto.isNotBlank() &&
            costosFijos.isNotBlank() &&
            costoVariable.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cálculos de Datos - Productos", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF003366))
            )
        },
        bottomBar = {
            // Botón Volver Fijo en la parte inferior
            CustomButton(
                text = "Volver",
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp)
            )
        },
        containerColor = Color(0xFFF5F5F5) // Fondo gris claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Campos de Entrada
            CampoEntrada(label = "Precio Base", value = precioBase, onValueChange = { precioBase = it })
            CampoEntrada(label = "Costo del Producto", value = costoProducto, onValueChange = { costoProducto = it })
            CampoEntrada(label = "Costos Fijos", value = costosFijos, onValueChange = { costosFijos = it })
            CampoEntrada(label = "Costo Variable Unitario", value = costoVariable, onValueChange = { costoVariable = it })

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Calcular
            CustomButton(
                text = "Calcular",
                onClick = {
                    if (camposLlenos) {
                        val entradas = mapOf(
                            "precioBase" to precioBase,
                            "costo" to costoProducto,
                            "costosFijos" to costosFijos,
                            "costoVariable" to costoVariable
                        )
                        // Asegura que el ViewModel actualice correctamente
                        try {
                            viewModel.actualizarEntradas(entradas)
                            navController.navigate("seleccion_calculo_producto")
                        } catch (e: Exception) {
                            mostrarAlerta = true
                        }
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
                MostrarAlerta("Por favor, llena todos los campos o verifica los datos.") {
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
        onValueChange = {
            if (it.all { char -> char.isDigit() }) onValueChange(it) // Solo números positivos
        },
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
