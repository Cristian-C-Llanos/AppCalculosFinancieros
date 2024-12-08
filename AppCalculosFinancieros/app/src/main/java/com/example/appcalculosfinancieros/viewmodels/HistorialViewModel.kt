package com.example.appcalculosfinancieros.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Estructura de datos para el historial de cálculos
data class CalculoHistorial(
    val nombre: String,                // Nombre del cálculo o segmento
    val entradas: Map<String, String>, // Valores de las entradas como clave-valor
    val formula: String,               // Fórmula utilizada para el cálculo
    val resultado: String              // Resultado del cálculo
)

class HistorialViewModel : ViewModel() {
    // MutableStateFlow para almacenar la lista de cálculos
    private val _historial = MutableStateFlow<List<CalculoHistorial>>(emptyList())
    val historial: StateFlow<List<CalculoHistorial>> = _historial

    // Función para agregar un nuevo cálculo al historial
    fun agregarCalculo(calculo: CalculoHistorial) {
        _historial.value = _historial.value + calculo
    }

    // Función para limpiar el historial (al cerrar la app o reiniciar)
    fun limpiarHistorial() {
        _historial.value = emptyList()
    }
}
