package com.example.appcalculosfinancieros.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appcalculosfinancieros.views.HistorialCalculos
import com.example.appcalculosfinancieros.views.SeleccionSegmento
import com.example.appcalculosfinancieros.views.empleado.CapturaDatosEmpleado
import com.example.appcalculosfinancieros.views.empleado.SeleccionCalculoEmpleado
import com.example.appcalculosfinancieros.views.empleador.CapturaDatosEmpleador
import com.example.appcalculosfinancieros.views.empleador.SeleccionCalculoEmpleador
import com.example.appcalculosfinancieros.views.productos.CapturaDatosProductos
import com.example.appcalculosfinancieros.views.productos.SeleccionCalculoProducto
import com.example.appcalculosfinancieros.viewmodels.CalculosEmpleadoViewModel
import com.example.appcalculosfinancieros.viewmodels.CalculosEmpleadorViewModel
import com.example.appcalculosfinancieros.viewmodels.CalculosProductosViewModel
import com.example.appcalculosfinancieros.viewmodels.HistorialViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    // ViewModels compartidos
    val historialViewModel: HistorialViewModel = viewModel()
    val productosViewModel: CalculosProductosViewModel = viewModel()
    val empleadoViewModel: CalculosEmpleadoViewModel = viewModel()
    val empleadorViewModel: CalculosEmpleadorViewModel = viewModel()

    // Definición del grafo de navegación
    NavHost(navController = navController, startDestination = "menu_principal") {

        // Pantalla Principal
        composable("menu_principal") {
            SeleccionSegmento(navController)
        }

        // Segmento Productos
        composable("captura_datos_productos") {
            CapturaDatosProductos(navController, productosViewModel)
        }
        composable("seleccion_calculo_producto") {
            SeleccionCalculoProducto(navController, productosViewModel, historialViewModel)
        }

        // Segmento Empleados
        composable("captura_datos_empleado") {
            CapturaDatosEmpleado(navController, empleadoViewModel)
        }
        composable("seleccion_calculo_empleado") {
            SeleccionCalculoEmpleado(navController, empleadoViewModel, historialViewModel)
        }

        // Segmento Empleador
        composable("captura_datos_empleador") {
            CapturaDatosEmpleador(navController, empleadorViewModel)
        }
        composable("seleccion_calculo_empleador") {
            SeleccionCalculoEmpleador(navController, empleadorViewModel, historialViewModel)
        }

        // Historial de Cálculos
        composable("historial_calculos") {
            HistorialCalculos(navController, historialViewModel)
        }
    }
}
