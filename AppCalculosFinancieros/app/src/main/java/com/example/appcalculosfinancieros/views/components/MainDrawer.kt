package com.example.appcalculosfinancieros.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainDrawer(navController: NavController, closeDrawer: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(240.dp), // Ajuste del ancho del menú
        color = Color(0xFF003366) // Fondo del menú lateral
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Menú Principal",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            DrawerButton("Cálculos de Productos") {
                navController.navigate("captura_datos_productos")
                closeDrawer()
            }
            DrawerButton("Cálculos de Empleados") {
                navController.navigate("captura_datos_empleado")
                closeDrawer()
            }
            DrawerButton("Cálculos de Empleador") {
                navController.navigate("captura_datos_empleador")
                closeDrawer()
            }
            DrawerButton("Historial de Cálculos") {
                navController.navigate("historial_calculos")
                closeDrawer()
            }
        }
    }
}

@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00509D)), // Azul más claro
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, fontSize = 18.sp, color = Color.White)
    }
}
