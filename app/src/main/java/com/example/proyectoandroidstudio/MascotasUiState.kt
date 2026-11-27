package com.example.proyectoandroidstudio

data class MascotasUiState(
    val mascotas: List<Mascotas> = emptyList(),
    val mostrarFormulario: Boolean = false,
    val mascotaParaEditar: Mascotas? = null,
    val mascotaParaEliminar: Mascotas? = null
)
