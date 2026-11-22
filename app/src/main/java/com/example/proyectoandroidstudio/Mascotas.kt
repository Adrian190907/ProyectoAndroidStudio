package com.example.proyectoandroidstudio

data class Mascotas(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val peso: Double? = null,
    val vacunado: Boolean = false,
    val fechaNacimiento: String = "Sin registrar",
    val sexo: String = "Sin registrar",
    val veterinario: String = "Sin registrar",
    val clinica: String = "Sin registrar",
    val direccion: String = "Sin registrar",
    val descripcion: String = "Sin descripción"
)
