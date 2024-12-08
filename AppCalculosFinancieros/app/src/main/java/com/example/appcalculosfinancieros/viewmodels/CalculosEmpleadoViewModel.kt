package com.example.appcalculosfinancieros.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculosEmpleadoViewModel : ViewModel() {

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

    // Cálculo de Salario Neto
    fun calcularSalarioNeto() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Salario Neto = Salario Base - (Pensión 4% + Salud 4%)"
        _resultado.value = String.format("%.2f", salarioBase - (salarioBase * 0.08))
    }

    // Cálculo de Hora Extra Diurna
    fun calcularHoraExtraDiurna() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Hora Extra Diurna = (Salario Base / 240) * 1.25"
        _resultado.value = String.format("%.2f", (salarioBase / 240) * 1.25)
    }

    // Cálculo de Hora Extra Nocturna
    fun calcularHoraExtraNocturna() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Hora Extra Nocturna = (Salario Base / 240) * 1.75"
        _resultado.value = String.format("%.2f", (salarioBase / 240) * 1.75)
    }

    // Cálculo de Hora Dominical/Festiva
    fun calcularHoraDominicalFestiva() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        _formula.value = "Hora Dominical/Festiva = (Salario Base / 240) * 2"
        _resultado.value = String.format("%.2f", (salarioBase / 240) * 2)
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
