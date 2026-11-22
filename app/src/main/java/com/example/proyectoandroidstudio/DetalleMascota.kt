package com.example.proyectoandroidstudio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoandroidstudio.ui.theme.*

@Composable
fun DetalleMascota(
    mascotas: List<Mascotas>,
    idMascota: String,
    onVolver: () -> Unit,
    onEditar: () -> Unit = {},
    onEliminar: () -> Unit = {}
) {
    val mascota = mascotas.find { it.id == idMascota }

    Scaffold(containerColor = FondoAmiko) { innerPadding ->
        if (mascota == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("No se ha encontrado la mascota.", color = TextoAmiko)

                TextButton(onClick = onVolver) {
                    Text("Volver a la lista", color = VerdeAmiko)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onVolver) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Volver",
                            tint = VerdeAmiko
                        )
                    }

                    Text(
                        text = "Mi mascota",
                        modifier = Modifier.weight(1f),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoAmiko
                    )

                    IconButton(onClick = onEditar) {
                        Icon(
                            Icons.Default.Edit,
                            "Editar mascota",
                            tint = VerdeAmiko
                        )
                    }

                    IconButton(onClick = onEliminar) {
                        Icon(
                            Icons.Default.Delete,
                            "Eliminar mascota",
                            tint = CoralAmiko
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(FondoFotoAmiko)
                        .border(
                            1.dp,
                            BordeFotoAmiko,
                            RoundedCornerShape(26.dp)
                        )
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = mascota.nombre,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoAmiko
                        )

                        Text(
                            text = mascota.raza,
                            fontSize = 14.sp,
                            color = TextoSecundarioAmiko
                        )

                        Text(
                            text = "${mascota.especie} · ${mascota.edad} " +
                                    if (mascota.edad == 1) "año" else "años",
                            fontSize = 12.sp,
                            color = TextoSecundarioAmiko
                        )

                        Text(
                            text = if (mascota.vacunado) {
                                "Vacunado/a: sí"
                            } else {
                                "Vacunado/a: no"
                            },
                            fontSize = 12.sp,
                            color = TextoSecundarioAmiko
                        )
                    }

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(VerdeSuaveAmiko)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = VerdeAmiko
                        )

                        Text(
                            text = mascota.sexo,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdeAmiko
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DatoMascota(
                        titulo = "Fecha de nacimiento",
                        valor = mascota.fechaNacimiento,
                        icono = R.drawable.baseline_cake_24,
                        modifier = Modifier
                            .weight(1f)
                            .height(140.dp)
                    )

                    DatoMascota(
                        titulo = "Peso actual",
                        valor = if (mascota.peso == null) {
                            "Sin registrar"
                        } else {
                            "${mascota.peso} kg"
                        },
                        icono = R.drawable.ic_peso_24,
                        modifier = Modifier
                            .weight(1f)
                            .height(140.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = VerdeSuaveAmiko
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            "SU VETERINARIO/A",
                            fontSize = 10.sp,
                            color = TextoSecundarioAmiko
                        )
                        Text(
                            mascota.veterinario,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoAmiko
                        )
                        Text(
                            mascota.clinica,
                            fontSize = 12.sp,
                            color = TextoSecundarioAmiko
                        )
                    }
                }

                Spacer(Modifier.height(22.dp))

                Text(
                    text = "Así es ${mascota.nombre}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoAmiko
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = mascota.descripcion,
                    fontSize = 14.sp,
                    color = TextoSecundarioAmiko
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Su lugar de cuidado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoAmiko
                )

                Spacer(Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = FondoTarjetaAmiko
                    )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(FondoFotoAmiko)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = VerdeAmiko,
                            modifier = Modifier.size(24.dp)
                        )

                        Column(
                        ) {
                            Text(
                                text = mascota.clinica,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextoAmiko
                            )

                            Text(
                                text = mascota.direccion,
                                fontSize = 12.sp,
                                color = TextoSecundarioAmiko
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DatoMascota(
    titulo: String,
    valor: String,
    icono: Int,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FondoTarjetaAmiko)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(icono),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = CoralAmiko
            )

            Text(
                text = titulo,
                fontSize = 11.sp,
                color = TextoSecundarioAmiko
            )

            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextoAmiko
            )
        }
    }
}
