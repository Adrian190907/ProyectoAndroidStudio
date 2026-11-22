package com.example.proyectoandroidstudio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
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
fun PantallaMisMascotas(
    mascotas: List<Mascotas>,
    onMascota: (Mascotas) -> Unit,
    onAniadirMascota: () -> Unit = {}
) {
    Scaffold(
        containerColor = FondoAmiko,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAniadirMascota,
                containerColor = VerdeAmiko,
                contentColor = FondoTarjetaAmiko
            ) {
                Icon(Icons.Default.Add, "Añadir mascota")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        tint = VerdeAmiko
                    )
                }

                Text(
                    text = "Mis mascotas",
                    color = TextoAmiko,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Tu pequeña familia.",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoAmiko
                )

                Text(
                    text = when (mascotas.size) {
                        0 -> "Todavía no tienes mascotas."
                        1 -> "1 compañero, todo un mundo de cariño."
                        else -> "${mascotas.size} compañeros, todo un mundo de cariño."
                    },
                    color = TextoSecundarioAmiko
                )
            }

            ListaMascotas(
                mascotas = mascotas,
                onMascota = onMascota,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
fun ListaMascotas(
    mascotas: List<Mascotas>,
    onMascota: (Mascotas) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 88.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(mascotas, key = { it.id }) { mascota ->
            TarjetaMascota(
                mascota = mascota,
                onClick = { onMascota(mascota) }
            )
        }
    }
}

@Composable
fun TarjetaMascota(
    mascota: Mascotas,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = FondoTarjetaAmiko)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(FondoFotoAmiko)
                    .border(
                        1.dp,
                        BordeFotoAmiko,
                        RoundedCornerShape(16.dp)
                    )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = mascota.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoAmiko
                )

                Text(
                    text = mascota.raza,
                    color = TextoSecundarioAmiko
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_cake_24),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = CoralAmiko
                    )

                    Text(
                        text = if (mascota.edad == 1) {
                            "1 año"
                        } else {
                            "${mascota.edad} años"
                        },
                        color = CoralAmiko,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = VerdeAmiko
            )
        }
    }
}

