package com.example.miprimerainterfaz // <- deja el package de tu proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FichaAlumno()
            }
        }
    }
}

@Composable
fun FichaAlumno() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var esDelegado by rememberSaveable { mutableStateOf(false) }
    var avisos by rememberSaveable { mutableStateOf(true) }
    var resumen by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CabeceraFicha(nombre = nombre)

        OutlinedTextField(
            value = nombre,
            onValueChange = { textoNuevo -> nombre = textoNuevo },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        OpcionCheck(
            texto = "Soy delegado de clase",
            marcada = esDelegado,
            onCambio = { esDelegado = it },
        )
        OpcionSwitch(
            texto = "Recibir avisos",
            activada = avisos,
            onCambio = { avisos = it },
        )

        Button(
            onClick = {
                resumen = if (nombre.isBlank()) {
                    "Escribe un nombre antes de guardar"
                } else {
                    val rol = if (esDelegado) "delegado" else "alumno"
                    val estadoAvisos = if (avisos) "con avisos" else "sin avisos"
                    "Guardado: $nombre ($rol, $estadoAvisos)"
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Guardar")
        }

        if (resumen.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = resumen,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

@Composable
fun CabeceraFicha(nombre: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = nombre.take(1).uppercase().ifEmpty { "?" },
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Column {
            Text(
                text = if (nombre.isBlank()) "Alumno sin nombre" else nombre,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Ficha de alumno",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun OpcionCheck(
    texto: String,
    marcada: Boolean,
    onCambio: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = marcada, onCheckedChange = onCambio)
        Text(texto)
    }
}

@Composable
fun OpcionSwitch(
    texto: String,
    activada: Boolean,
    onCambio: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(texto)
        Switch(checked = activada, onCheckedChange = onCambio)
    }
}

@Preview(showBackground = true)
@Composable
fun FichaAlumnoPreview() {
    MaterialTheme {
        FichaAlumno()
    }
}