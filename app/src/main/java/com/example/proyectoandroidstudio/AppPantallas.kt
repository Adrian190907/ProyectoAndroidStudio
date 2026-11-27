package com.example.proyectoandroidstudio

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoandroidstudio.ui.theme.colores

@Composable
fun App(viewModel: MascotasViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState = viewModel.uiState
    val editar = uiState.mascotaParaEditar
    val eliminar = uiState.mascotaParaEliminar

    MaterialTheme(colorScheme = colores) {
        NavHost(
            navController = navController,
            startDestination = Rutas.MIS_MASCOTAS
        ) {
            composable(Rutas.MIS_MASCOTAS) {
                PantallaMisMascotas(
                    mascotas = uiState.mascotas,
                    onMascota = { mascota -> navController.navigate(Rutas.detalle(mascota.id)) },
                    onAniadirMascota = { viewModel.aniadirMascota() }
                )
            }

            composable(
                route = Rutas.DETALLE,
                arguments = listOf(navArgument("idMascota") { type = NavType.StringType })
            ) { backStackEntry ->

                val idMascota = backStackEntry.arguments?.getString("idMascota")
                val seleccionada = uiState.mascotas.find { it.id == idMascota }

                if (seleccionada != null) {
                    DetalleMascota(
                        mascotas = uiState.mascotas,
                        idMascota = seleccionada.id,
                        onVolver = { navController.popBackStack() },
                        onEditar = { viewModel.editarMascota(seleccionada) },
                        onEliminar = { viewModel.eliminarMascota(seleccionada) }
                    )
                }
            }
        }

        if (uiState.mostrarFormulario || editar != null) {
            FormularioMascotaDialog(
                mascotaInicial = editar,
                onCancelar = { viewModel.cancelarFormulario() },
                onGuardar = { nombre, especie, raza, edad, peso, vacunado, fechaNacimiento, sexo, veterinario, clinica, direccion, descripcion ->
                    viewModel.guardarMascota(nombre, especie, raza, edad, peso, vacunado, fechaNacimiento, sexo, veterinario, clinica, direccion, descripcion)
                }
            )
        }

        if (eliminar != null) {
            ConfirmarEliminarMascotaDialog(
                mascota = eliminar,
                onCancelar = { viewModel.cancelarEliminar() },
                onConfirmar = { viewModel.confirmarEliminar()
                    navController.popBackStack()
                }
            )
        }
    }
}
