package com.example.proyectoandroidstudio

object Rutas {
    const val MIS_MASCOTAS = "mis_mascotas"
    const val DETALLE = "detalle/{idMascota}"

    fun detalle(idMascota: String) = "detalle/$idMascota"
}
