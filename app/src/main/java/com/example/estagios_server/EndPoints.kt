package com.example.estagios_server

import retrofit2.Call
import retrofit2.http.GET

interface EndPoints {
    @GET("/api/cursos")
    fun getCursos(): Call<List<Escola>>
}
