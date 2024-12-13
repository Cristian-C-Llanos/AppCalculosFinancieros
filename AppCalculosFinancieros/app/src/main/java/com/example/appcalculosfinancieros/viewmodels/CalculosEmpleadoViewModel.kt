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
        _entradas.value = nuevasEntradas.filterValues { it.isNotBlank() }
    }

    // Cálculo de Salario Neto
    fun calcularSalarioNeto() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        val pensionSalud = salarioBase * 0.08 // Pensión 4% + Salud 4%

        _formula.value = "Salario Neto = Salario Base - (Pensión + Salud)"
        _resultado.value = String.format("%.2f", salarioBase - pensionSalud)
    }

    // Cálculo de Deducciones (con 8% adicional al salario neto)
    fun calcularDeduccionesNomina() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        val deducciones = _entradas.value["deducciones"]?.toDoubleOrNull() ?: 0.0
        val adicionales = salarioBase * 0.08 // Salario Base * (Pensión 4% + Salud 4%)

        _formula.value = "Deducciones = Deducciones + Salario Base * (Pensión 4% + Salud 4%)"
        _resultado.value = String.format("%.2f", deducciones + adicionales)
    }

    // Cálculo de Horas Extras
    fun calcularHorasExtras() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        val horaExtraDiurna = _entradas.value["horasDiurnas"]?.toDoubleOrNull() ?: 0.0
        val horaExtraNocturna = _entradas.value["horasNocturnas"]?.toDoubleOrNull() ?: 0.0
        val horaFestiva = _entradas.value["horasFestivas"]?.toDoubleOrNull() ?: 0.0

        if (salarioBase <= 0) {
            _formula.value = "Error: Salario Base inválido"
            _resultado.value = "0.0"
            return
        }

        val valorHora = salarioBase / 240
        val totalHorasExtras = (horaExtraDiurna * valorHora * 1.25) +
                (horaExtraNocturna * valorHora * 1.75) +
                (horaFestiva * valorHora * 2.0)

        _formula.value = "Horas Extras = (Diurna * 1.25) + (Nocturna * 1.75) + (Festiva * 2.0)"
        _resultado.value = String.format("%.2f", totalHorasExtras)
    }

    // Cálculo de Bonificaciones
    fun calcularBonificaciones() {
        val salarioBase = _entradas.value["salarioBase"]?.toDoubleOrNull() ?: 0.0
        val porcentajeBonificacion = _entradas.value["porcentajeBonificacion"]?.toDoubleOrNull() ?: 0.0

        if (salarioBase <= 0 || porcentajeBonificacion < 0) {
            _formula.value = "Error: Datos inválidos"
            _resultado.value = "0.0"
            return
        }

        val bonificacion = salarioBase * (porcentajeBonificacion / 100)

        _formula.value = "Bonificación = Salario Base * (% Bonificación / 100)"
        _resultado.value = String.format("%.2f", bonificacion)
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
