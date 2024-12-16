package com.example.estagios_server

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ESA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esa)

        val listViewCursos: ListView = findViewById(R.id.listViewCursos)

        // Obtém os cursos da API
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()

        call.enqueue(object : Callback<List<Escola>> {
            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    val cursos = response.body()?.find { it.escola == "ESA" }?.cursos ?: emptyList()
                    val adapter = CursoAdapter(this@ESA, cursos)
                    listViewCursos.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Escola>>, t: Throwable) {
            }
        })
        // Configurar o clique no botão "Estágios"
        val buttonEstagios: Button = findViewById(R.id.button_estagios)
        buttonEstagios.setOnClickListener {
            // Inicia a nova atividade
            val intent = Intent(this@ESA, MapaEstagios::class.java)
            startActivity(intent)
        }

        val buttonVoltar: Button = findViewById(R.id.button_voltar)
        buttonVoltar.setOnClickListener {
            // Finaliza a atividade atual e retorna à anterior
            onBackPressed()
        }
    }
}