package com.example.proyectoandroidstudio.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val colores = lightColorScheme(
    primary = VerdeAmiko,
    onPrimary = BlancoAmiko,
    primaryContainer = VerdeSuaveAmiko,
    onPrimaryContainer = TextoAmiko,
    secondary = CoralAmiko,
    background = FondoAmiko,
    onBackground = TextoAmiko,
    surface = BlancoAmiko,
    onSurface = TextoAmiko,
    onSurfaceVariant = TextoSecundarioAmiko
)
