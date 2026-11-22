package com.example.proyectoandroidstudio

object MascotasRepository {
    private val listaMascotas = mutableListOf(
        Mascotas(
            nombre = "Luna",
            especie = "Perro",
            raza = "Golden retriever",
            edad = 3,
            peso = 28.0,
            vacunado = true,
            fechaNacimiento = "14 mar 2023",
            sexo = "Hembra",
            veterinario = "Dra. Elena Martín",
            clinica = "Clínica Vet Retiro",
            direccion = "Calle de Ibiza, 12 · Madrid",
            descripcion = "Cariñosa, curiosa y siempre lista para salir al Retiro. " +
                    "Le encanta traer su pelota y saludar a todos en el parque. " +
                    "Pasea tres veces al día y disfruta de los juegos de olfato. " +
                    "Come por la mañana y por la tarde; conviene evitar los premios " +
                    "con pollo, que no le sientan bien."
        ),

        Mascotas(
            nombre = "Milo",
            especie = "Gato",
            raza = "Europeo común",
            edad = 2,
            peso = 4.5,
            vacunado = true,
            fechaNacimiento = "8 jun 2024",
            sexo = "Macho",
            veterinario = "Dr. Pablo García",
            clinica = "Clínica Felina Centro",
            direccion = "Calle del Pez, 8 · Madrid",
            descripcion = "Tranquilo y curioso. Le gusta descansar junto a la " +
                    "ventana y jugar con su rascador. Prefiere los espacios " +
                    "silenciosos y necesita agua fresca durante todo el día."
        ),

        Mascotas(
            nombre = "Nala",
            especie = "Perro",
            raza = "Mestiza",
            edad = 5,
            peso = 16.0,
            vacunado = true,
            fechaNacimiento = "21 ene 2021",
            sexo = "Hembra",
            veterinario = "Dra. Laura Ruiz",
            clinica = "Centro Veterinario Norte",
            direccion = "Calle de Bravo Murillo, 40 · Madrid",
            descripcion = "Sociable y juguetona. Disfruta de los paseos largos " +
                    "y de buscar su pelota. Come dos veces al día y le gusta " +
                    "descansar después de cada paseo."
        )
    )

    fun getMascotas(): List<Mascotas> = listaMascotas.toList()

    fun getMascotaById(id: String): Mascotas? = listaMascotas.find { it.id == id }

    fun addMascota(mascota: Mascotas) {
        listaMascotas.add(mascota)
    }

    fun updateMascota(mascota: Mascotas) {
        val index = listaMascotas.indexOfFirst { it.id == mascota.id }
        if (index != -1)
            listaMascotas[index] = mascota
    }

    fun deleteMascota(id: String) {
        listaMascotas.removeAll { it.id == id }
    }
}