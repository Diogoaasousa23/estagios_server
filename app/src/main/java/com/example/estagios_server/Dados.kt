package com.example.estagios_server

data class Escola(
    val escola: String,
    val cursos: List<Curso>
)

data class Curso(
    val nome: String
)
