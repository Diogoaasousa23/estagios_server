package com.example.estagios_server

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()
        call.enqueue(object : Callback<List<Escola>> {
            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    val escolas: List<Escola> = response.body()!!
                    val displayText = StringBuilder()
                    for (escola in escolas) {
                        displayText.append("Escola: ").append(escola.escola).append("\n")
                        for (curso in escola.cursos) {
                            displayText.append(" - Curso: ").append(curso.nome).append("\n")
                        }
                        displayText.append("\n") // Para separar escolas com uma linha em branco
                    }
                    findViewById<TextView>(R.id.txtDisplay).text = displayText.toString()
                }
            }

            override fun onFailure(call: Call<List<Escola>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
