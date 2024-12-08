package com.example.appcalculosfinancieros.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculosProductosViewModel : ViewModel() {

    // Variables para almacenar las entradas
    private val _entradas = MutableStateFlow<Map<String, String>>(emptyMap())
    val entradas: StateFlow<Map<String, String>> = _entradas

    // Resultado del cálculo
    private val _resultado = MutableStateFlow("0.0")
    val resultado: StateFlow<String> = _resultado

    // Fórmula actual
    private val _formula = MutableStateFlow("")
    val formula: StateFlow<String> = _formula

    // Actualizar entradas
    fun actualizarEntradas(nuevasEntradas: Map<String, String>) {
        _entradas.value = nuevasEntradas.filterValues { it.isNotBlank() } // Evita entradas vacías
    }

    // Cálculo de Precio con IVA
    fun calcularPrecioConIVA() {
        val precioBase = _entradas.value["precioBase"]?.toDoubleOrNull()
        if (precioBase != null) {
            _formula.value = "Precio con IVA = Precio Base * 1.19"
            _resultado.value = String.format("%.2f", precioBase * 1.19)
        } else {
            _formula.value = "Fórmula no válida"
            _resultado.value = "Error: Precio Base inválido"
        }
    }

    // Cálculo de Margen de Ganancia
    fun calcularMargenDeGanancia() {
        val precioVenta = _entradas.value["precioBase"]?.toDoubleOrNull() ?: 0.0
        val costo = _entradas.value["costo"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Margen = ((Precio Venta - Costo) / Precio Venta) * 100"
        _resultado.value = if (precioVenta > 0) {
            String.format("%.2f", ((precioVenta - costo) / precioVenta) * 100)
        } else "0.0"
    }

    // Cálculo de Punto de Equilibrio
    fun calcularPuntoDeEquilibrio() {
        val costosFijos = _entradas.value["costosFijos"]?.toDoubleOrNull() ?: 0.0
        val precioVenta = _entradas.value["precioBase"]?.toDoubleOrNull() ?: 0.0
        val costoVariable = _entradas.value["costoVariable"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Punto Equilibrio = Costos Fijos / (Precio Venta - Costo Variable)"
        _resultado.value = if (precioVenta > costoVariable) {
            String.format("%.2f", costosFijos / (precioVenta - costoVariable))
        } else "No válido (Precio Venta <= Costo Variable)"
    }

    // Cálculo de ROI
    fun calcularROI() {
        val ingresos = _entradas.value["precioBase"]?.toDoubleOrNull() ?: 0.0
        val inversion = _entradas.value["costo"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "ROI = ((Ingresos - Inversión) / Inversión) * 100"
        _resultado.value = if (inversion > 0) {
            String.format("%.2f", ((ingresos - inversion) / inversion) * 100)
        } else "0.0"
    }

    // Crear historial
    fun crearHistorial(nombre: String, segmento: String): CalculoHistorial {
        return CalculoHistorial(
            nombre = nombre,
            entradas = _entradas.value,
            formula = _formula.value,
            resultado = _resultado.value
        )
    }
}
