package com.example.estagios_server

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.estagios_server.entities.Comment
import com.example.estagios_server.viewModel.CommentViewModel
import com.example.estagios_server.viewModel.CommentViewModelFactory

class AddCommentActivity : AppCompatActivity() {

    private lateinit var editComment: EditText
    private lateinit var editSchool: EditText

    private val wordViewModel: CommentViewModel by viewModels {
        CommentViewModelFactory((application as EstagiosServerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)

        // Inicializar campos de entrada
        editComment = findViewById(R.id.edit_comment)
        editSchool = findViewById(R.id.edit_school)

        // Configurar botão de salvar
        val buttonSave = findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editComment.text) || TextUtils.isEmpty(editSchool.text)) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos para salvar o comentário.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val word = Comment(
                    comment = editComment.text.toString(),
                    school = editSchool.text.toString()
                )
                wordViewModel.insert(word) // Insere no banco de dados
                Toast.makeText(this, "Comentário salvo com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
