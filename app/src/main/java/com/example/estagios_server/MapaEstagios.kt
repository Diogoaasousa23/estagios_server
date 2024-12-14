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
        val initialLocation = LatLng(41.1496, -8.6109) // Exemplo: Braga, Portugal
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12f))
    }
}
