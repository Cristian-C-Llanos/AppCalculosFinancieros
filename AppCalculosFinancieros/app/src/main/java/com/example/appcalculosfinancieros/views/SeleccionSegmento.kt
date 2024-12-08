package com.example.appcalculosfinancieros.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionSegmento(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TÍTULO DE LA APP", color = Color.White, fontSize = 20.sp) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SegmentButton("Productos") { navController.navigate("captura_datos_productos") }
            SegmentButton("Empleados") { navController.navigate("captura_datos_empleado") }
            SegmentButton("Empleador") { navController.navigate("captura_datos_empleador") }
            SegmentButton("Historial de cálculos") { navController.navigate("historial_calculos") }
        }
    }
}

@Composable
fun SegmentButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003366)), // Azul oscuro
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text, fontSize = 24.sp, color = Color.White) // Texto grande y blanco
    }
}
