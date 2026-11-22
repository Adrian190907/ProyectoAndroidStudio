package com.example.proyectoandroidstudio

import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.proyectoandroidstudio.ui.theme.colores

@Composable
fun App() {
    var mascotas by remember { mutableStateOf(MascotasRepository.getMascotas()) }

    var idSeleccionado by rememberSaveable { mutableStateOf<String?>(null) }

    var mostrarFormulario by rememberSaveable { mutableStateOf(false) }

    var idParaEditar by rememberSaveable { mutableStateOf<String?>(null) }

    var idParaEliminar by rememberSaveable { mutableStateOf<String?>(null) }

    val seleccionada = mascotas.find { it.id == idSeleccionado }

    val editar = mascotas.find { it.id == idParaEditar }

    val eliminar = mascotas.find { it.id == idParaEliminar }

    BackHandler(
        enabled = idSeleccionado != null &&
                !mostrarFormulario &&
                idParaEditar == null &&
                idParaEliminar == null
    ) {
        idSeleccionado = null
    }

    MaterialTheme(colorScheme = colores) {
        if (seleccionada == null) {
            PantallaMisMascotas(
                mascotas = mascotas,
                onMascota = {
                    idSeleccionado = it.id
                },
                onAniadirMascota = {
                    mostrarFormulario = true
                }
            )
        } else {
            DetalleMascota(
                mascotas = mascotas,
                idMascota = seleccionada.id,
                onVolver = {
                    idSeleccionado = null
                },
                onEditar = {
                    idParaEditar = seleccionada.id
                },
                onEliminar = {
                    idParaEliminar = seleccionada.id
                }
            )
        }

        if (mostrarFormulario || editar != null) {
            FormularioMascotaDialog(
                mascotaInicial = editar,
                onCancelar = {
                    mostrarFormulario = false
                    idParaEditar = null
                },
                onGuardar = { nombre, especie, raza, edad, peso, vacunado, fechaNacimiento, sexo, veterinario, clinica, direccion, descripcion ->
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

                    if (editar == null) {
                        MascotasRepository.addMascota(mascota)
                    } else {
                        MascotasRepository.updateMascota(mascota)
                    }

                    mascotas = MascotasRepository.getMascotas()
                    mostrarFormulario = false
                    idParaEditar = null
                }
            )
        }

        if (eliminar != null) {
            ConfirmarEliminarMascotaDialog(
                mascota = eliminar,
                onCancelar = {
                    idParaEliminar = null
                },
                onConfirmar = {
                    MascotasRepository.deleteMascota(eliminar.id)
                    mascotas = MascotasRepository.getMascotas()
                    idParaEliminar = null
                    idSeleccionado = null
                }
            )
        }
    }
}