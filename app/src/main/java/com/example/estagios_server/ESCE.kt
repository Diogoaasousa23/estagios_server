package com.example.estagios_server

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ESCE : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esce)

        val listViewCursos: ListView = findViewById(R.id.listViewCursos)
        val checkboxFavorito: CheckBox = findViewById(R.id.checkbox_favorito)

        // Gerenciar estado do CheckBox com SharedPreferences
        val sharedPreferences = getSharedPreferences("FavoritosPrefs", Context.MODE_PRIVATE)
        val isFavorito = sharedPreferences.getBoolean("ESCE_Favorito", false)

        // Atualizar estado do checkbox
        checkboxFavorito.isChecked = isFavorito

        // Listener para salvar o estado do checkbox
        checkboxFavorito.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("ESCE_Favorito", isChecked)
            editor.apply()
        }

        // Obtém os cursos da API
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()

        call.enqueue(object : Callback<List<Escola>> {
            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    val cursos = response.body()?.find { it.escola == "ESCE" }?.cursos ?: emptyList()
                    val adapter = CursoAdapter(this@ESCE, cursos)
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
            val intent = Intent(this@ESCE, MapaEstagios::class.java)
            startActivity(intent)
        }

        val buttonVoltar: Button = findViewById(R.id.button_voltar)
        buttonVoltar.setOnClickListener {
            // Finaliza a atividade atual e retorna à anterior
            onBackPressed()
        }
    }
}
