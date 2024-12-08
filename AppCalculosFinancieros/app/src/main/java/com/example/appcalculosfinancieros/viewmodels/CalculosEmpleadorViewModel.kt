package com.example.appcalculosfinancieros.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculosEmpleadorViewModel : ViewModel() {

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
        _entradas.value = nuevasEntradas.filterValues { it.isNotBlank() }
    }

    // Cálculo de Aportes Parafiscales
    fun calcularAportesParafiscales() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull()
        if (salarioBase != null) {
            _formula.value = "Aportes Parafiscales = Salario Base * 9%"
            _resultado.value = String.format("%.2f", salarioBase * 0.09)
        } else {
            _formula.value = "Fórmula no válida"
            _resultado.value = "Error: Salario Base inválido"
        }
    }

    // Cálculo de Seguridad Social
    fun calcularSeguridadSocial() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull()
        if (salarioBase != null) {
            _formula.value = "Seguridad Social = Salario Base * 20.5%"
            _resultado.value = String.format("%.2f", salarioBase * 0.205)
        } else {
            _formula.value = "Fórmula no válida"
            _resultado.value = "Error: Salario Base inválido"
        }
    }

    // Cálculo de Prestaciones Sociales
    fun calcularPrestacionesSociales() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull()
        if (salarioBase != null) {
            _formula.value = "Prestaciones Sociales = Salario Base * 21.83%"
            _resultado.value = String.format("%.2f", salarioBase * 0.2183)
        } else {
            _formula.value = "Fórmula no válida"
            _resultado.value = "Error: Salario Base inválido"
        }
    }

    // Cálculo del Costo Total de Nómina
    fun calcularCostoTotalNomina() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull()
        if (salarioBase != null) {
            val aportesParafiscales = salarioBase * 0.09
            val seguridadSocial = salarioBase * 0.205
            val prestaciones = salarioBase * 0.2183
            val totalNomina = salarioBase + aportesParafiscales + seguridadSocial + prestaciones

            _formula.value = "Costo Total de Nómina = Salario Base + Aportes + Seguridad + Prestaciones"
            _resultado.value = String.format("%.2f", totalNomina)
        } else {
            _formula.value = "Fórmula no válida"
            _resultado.value = "Error: Salario Base inválido"
        }
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
