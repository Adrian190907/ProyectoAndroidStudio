package com.example.proyectoandroidstudio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MascotasViewModel : ViewModel() {
    var uiState by mutableStateOf(MascotasUiState())
        private set

    init { uiState = uiState.copy(mascotas = MascotasRepository.getMascotas()) }

    fun aniadirMascota() { uiState = uiState.copy(mostrarFormulario = true) }

    fun editarMascota(mascota: Mascotas) { uiState = uiState.copy(mascotaParaEditar = mascota) }

    fun cancelarFormulario() { uiState = uiState.copy(mostrarFormulario = false, mascotaParaEditar = null) }

    fun guardarMascota(
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        peso: Double?,
        vacunado: Boolean,
        fechaNacimiento: String,
        sexo: String,
        veterinario: String,
        clinica: String,
        direccion: String,
        descripcion: String
    ) {
        val editar = uiState.mascotaParaEditar
        val mascota = Mascotas(
            id = editar?.id ?: java.util.UUID.randomUUID().toString(),
            nombre = nombre,
            especie = especie,
            raza = raza,
            edad = edad,
            peso = peso,
            vacunado = vacunado,
            fechaNacimiento = fechaNacimiento.ifBlank { "Sin registrar" },
            sexo = sexo.ifBlank { "Sin registrar" },
            veterinario = veterinario.ifBlank { "Sin registrar" },
            clinica = clinica.ifBlank { "Sin registrar" },
            direccion = direccion.ifBlank { "Sin registrar" },
            descripcion = descripcion.ifBlank { "Sin descripción" }
        )

        if (editar == null)
            MascotasRepository.addMascota(mascota)
        else
            MascotasRepository.updateMascota(mascota)


        uiState = uiState.copy(
            mascotas = MascotasRepository.getMascotas(),
            mostrarFormulario = false,
            mascotaParaEditar = null
        )
    }

    fun eliminarMascota(mascota: Mascotas) { uiState = uiState.copy(mascotaParaEliminar = mascota) }

    fun cancelarEliminar() { uiState = uiState.copy(mascotaParaEliminar = null) }

    fun confirmarEliminar() {
        val mascota = uiState.mascotaParaEliminar ?: return
        MascotasRepository.deleteMascota(mascota.id)
        uiState = uiState.copy(mascotas = MascotasRepository.getMascotas(), mascotaParaEliminar = null)
    }
}
