package com.example.proyectoandroidstudio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectoandroidstudio.ui.theme.FondoAmiko
import com.example.proyectoandroidstudio.ui.theme.TextoAmiko

@Composable
fun FormularioMascotaDialog(
    mascotaInicial: Mascotas? = null,
    onCancelar: () -> Unit,
    onGuardar: (nombre: String, especie: String, raza: String, edad: Int, peso: Double?, vacunado: Boolean, fechaNacimiento: String, sexo: String, veterinario: String, clinica: String, direccion: String, descripcion: String) -> Unit
) {

    var nombre by remember { mutableStateOf(mascotaInicial?.nombre ?: "") }
    var especie by remember { mutableStateOf(mascotaInicial?.especie ?: "") }
    var raza by remember { mutableStateOf(mascotaInicial?.raza ?: "") }
    var edad by remember { mutableStateOf(mascotaInicial?.edad?.toString() ?: "") }
    var peso by remember { mutableStateOf(mascotaInicial?.peso?.toString() ?: "") }
    var vacunado by remember { mutableStateOf(mascotaInicial?.vacunado ?: false) }
    var fechaNacimiento by remember { mutableStateOf(mascotaInicial?.fechaNacimiento ?: "") }
    var sexo by remember { mutableStateOf(mascotaInicial?.sexo ?: "") }
    var veterinario by remember { mutableStateOf(mascotaInicial?.veterinario ?: "") }
    var clinica by remember { mutableStateOf(mascotaInicial?.clinica ?: "") }
    var direccion by remember { mutableStateOf(mascotaInicial?.direccion ?: "") }
    var descripcion by remember { mutableStateOf(mascotaInicial?.descripcion ?: "") }

    var mostrarErrores by remember {
        mutableStateOf(false)
    }

    val nombreError = if (nombre.isBlank()) "El nombre es obligatorio" else null

    val especieError = if (especie.isBlank()) "La especie es obligatoria" else null

    val razaError = if (raza.isBlank()) "La raza es obligatoria" else null

    val edadNumero = edad.trim().toIntOrNull()

    val edadError = when {
        edad.isBlank() -> "La edad es obligatoria"
        edadNumero == null -> "Introduce la edad en años completos"
        edadNumero < 0 -> "La edad no puede ser negativa"
        else -> null
    }

    val pesoNumero = peso.trim().replace(',', '.').toDoubleOrNull()

    val pesoError = when {
        peso.isBlank() -> null
        pesoNumero == null -> "Introduce un peso válido"
        !pesoNumero.isFinite() || pesoNumero <= 0 -> "El peso debe ser un número positivo"
        else -> null
    }

    val formularioValido =
        nombreError == null &&
                especieError == null &&
                razaError == null &&
                edadError == null &&
                pesoError == null

    AlertDialog(
        onDismissRequest = onCancelar,
        containerColor = FondoAmiko,
        titleContentColor = TextoAmiko,
        textContentColor = TextoAmiko,
        title = {
            Text(if (mascotaInicial == null) "Añadir mascota" else "Editar mascota")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 420.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                CampoMascota(
                    value = nombre,
                    onCambio = { nombre = it },
                    label = "Nombre *",
                    error = if (mostrarErrores) nombreError else null
                )

                CampoMascota(
                    value = especie,
                    onCambio = { especie = it },
                    label = "Especie *",
                    error = if (mostrarErrores) especieError else null
                )

                CampoMascota(
                    value = raza,
                    onCambio = { raza = it },
                    label = "Raza *",
                    error = if (mostrarErrores) razaError else null
                )

                CampoMascota(
                    value = edad,
                    onCambio = { edad = it },
                    label = "Edad en años *",
                    error = if (mostrarErrores) edadError else null
                )

                CampoMascota(
                    value = peso,
                    onCambio = { peso = it },
                    label = "Peso en kg",
                    error = if (mostrarErrores) pesoError else null
                )

                CampoMascota(
                    value = fechaNacimiento,
                    onCambio = { fechaNacimiento = it },
                    label = "Fecha de nacimiento"
                )

                CampoMascota(
                    value = sexo,
                    onCambio = { sexo = it },
                    label = "Sexo"
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = vacunado,
                        onCheckedChange = { vacunado = it }
                    )
                    Text("Vacunado/a")
                }

                CampoMascota(
                    value = veterinario,
                    onCambio = { veterinario = it },
                    label = "Veterinario/a"
                )

                CampoMascota(
                    value = clinica,
                    onCambio = { clinica = it },
                    label = "Clínica"
                )

                CampoMascota(
                    value = direccion,
                    onCambio = { direccion = it },
                    label = "Dirección"
                )

                CampoMascota(
                    value = descripcion,
                    onCambio = { descripcion = it },
                    label = "Descripción"
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { if (formularioValido) { onGuardar(nombre.trim(), especie.trim(), raza.trim(), edad.trim().toInt(), pesoNumero, vacunado,fechaNacimiento.trim(), sexo.trim(), veterinario.trim(), clinica.trim(), direccion.trim(), descripcion.trim()) } else  mostrarErrores = true }
            ) {
                Text(if (mascotaInicial == null) "Guardar" else "Actualizar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
private fun CampoMascota(
    value: String,
    onCambio: (String) -> Unit,
    label: String,
    error: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onCambio,
        label = { Text(label) },
        singleLine = true,
        isError = error != null,
        supportingText = if (error != null) {
            { Text(error) }
        } else {
            null
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ConfirmarEliminarMascotaDialog(
    mascota: Mascotas,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        containerColor = FondoAmiko,
        titleContentColor = TextoAmiko,
        textContentColor = TextoAmiko,
        title = { Text("Eliminar mascota") },
        text = { Text("¿Quieres eliminar a ${mascota.nombre}?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text(
                    text = "Eliminar",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}