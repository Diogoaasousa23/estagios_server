package com.example.estagios_server

data class Escola(
    val escola: String,
    val cursos: List<Curso>
)

data class Curso(
    val nome: String,
    val locaisEstagio: List<LocalEstagio>
)

data class LocalEstagio(
    val nome: String,
    val latitude: Double,
    val longitude: Double
)
