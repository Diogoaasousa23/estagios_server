package com.example.estagios_server

import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapaEstagios : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_estagios)

        // Configura o mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Botão de retorno
        val buttonRetornar: Button = findViewById(R.id.btnVoltar)
        buttonRetornar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Configuração do botão de voltar para compatibilidade com o OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Fecha a atividade atual e retorna
            }
        })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true

        // Configura a localização inicial do mapa
        val initialLocation = LatLng(41.694581, -8.846861)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12f))

        // Carregar os locais de estágio do web service
        carregarLocais()
    }

    private fun carregarLocais() {
        val retrofit = ServiceBuilder.buildService(EndPoints::class.java)

        retrofit.getCursos().enqueue(object : Callback<List<Escola>> {
            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    val escolas = response.body()
                    escolas?.forEach { escola ->
                        escola.cursos.forEach { curso ->
                            curso.locaisEstagio.forEach { localEstagio ->
                                // Adiciona o marcador ao mapa com o número de alunos
                                googleMap.addMarker(
                                    MarkerOptions()
                                        .position(LatLng(localEstagio.latitude, localEstagio.longitude))
                                        .title("${localEstagio.nome} | Alunos: ${localEstagio.numeroAlunos}")
                                        .snippet("Escola: ${escola.escola} | Curso: ${curso.nome}")
                                )

                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Escola>>, t: Throwable) {
                // Tratar erro
            }
        })
    }
}
