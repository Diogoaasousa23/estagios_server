package com.example.estagios_server

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var checkbox1: CheckBox
    private lateinit var checkbox2: CheckBox
    private lateinit var checkbox3: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa o SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Encontra os checkboxes
        checkbox1 = findViewById(R.id.checkbox_som)
        checkbox2 = findViewById(R.id.checkbox_vibracao)
        checkbox3 = findViewById(R.id.checkbox_notificacoes)

        // Carrega o estado salvo dos checkboxes
        checkbox1.isChecked = sharedPreferences.getBoolean("checkbox1_state", false)
        checkbox2.isChecked = sharedPreferences.getBoolean("checkbox2_state", false)
        checkbox3.isChecked = sharedPreferences.getBoolean("checkbox3_state", false)

        // Configura os listeners para salvar o estado quando cada checkbox for clicado
        checkbox1.setOnCheckedChangeListener { _, isChecked ->
            saveCheckboxState("checkbox1_state", isChecked)
        }

        checkbox2.setOnCheckedChangeListener { _, isChecked ->
            saveCheckboxState("checkbox2_state", isChecked)
        }
        checkbox3.setOnCheckedChangeListener { _, isChecked ->
            saveCheckboxState("checkbox3_state", isChecked)
        }

        // Referência para os botões de imagem
        val buttonESE: ImageButton = findViewById(R.id.button_ese)
        val buttonESA: ImageButton = findViewById(R.id.button_esa)
        val buttonESTG: ImageButton = findViewById(R.id.button_estg)
        val buttonESS: ImageButton = findViewById(R.id.button_ess)
        val buttonESCE: ImageButton = findViewById(R.id.button_esce)
        val buttonESDL: ImageButton = findViewById(R.id.button_esdl)

        // Configura os listeners de clique para os botões de imagem
        buttonESE.setOnClickListener {
            openActivity(ESE::class.java)
        }

        buttonESA.setOnClickListener {
            openActivity(ESA::class.java)
        }

        buttonESTG.setOnClickListener {
            openActivity(ESTG::class.java)
        }

        buttonESS.setOnClickListener {
            openActivity(ESS::class.java)
        }

        buttonESCE.setOnClickListener {
            openActivity(ESCE::class.java)
        }

        buttonESDL.setOnClickListener {
            openActivity(ESDL::class.java)
        }

        // Referência para os novos botões
        val buttonAddComment: Button = findViewById(R.id.button_add_comment)
        val buttonViewComments: Button = findViewById(R.id.button_view_comments)

        // Configura os listeners de clique para os novos botões
        buttonAddComment.setOnClickListener {
            // Abre a atividade para adicionar um comentário
            openActivity(AddCommentActivity::class.java)
        }

        buttonViewComments.setOnClickListener {
            // Abre a atividade para ver os comentários
            openActivity(ViewCommentsActivity::class.java)
        }
    }

    // Função para salvar o estado dos checkboxes no SharedPreferences
    private fun saveCheckboxState(key: String, state: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, state)
        editor.apply()
    }

    // Função para abrir a atividade especificada
    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
