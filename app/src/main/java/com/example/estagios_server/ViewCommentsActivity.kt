package com.example.estagios_server

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estagios_server.adapter.CommentAdapter
import com.example.estagios_server.viewModel.CommentViewModel
import com.example.estagios_server.viewModel.CommentViewModelFactory

class ViewCommentsActivity : AppCompatActivity() {

    private val commentViewModel: CommentViewModel by viewModels {
        CommentViewModelFactory((application as EstagiosServerApplication).repository)
    }

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comment)

        // Inicializando o RecyclerView e o Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        commentAdapter = CommentAdapter()
        recyclerView.adapter = commentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa todos os comentários (LiveData)
        commentViewModel.getAllComments().observe(this) { comments ->
            comments?.let {
                commentAdapter.submitList(it) // Atualiza o adapter com todos os comentários
            }
        }

        // Configuração dos botões
        findViewById<Button>(R.id.button_ESTG).setOnClickListener {
            loadComments("ESTG")
        }
        findViewById<Button>(R.id.button_ESA).setOnClickListener {
            loadComments("ESA")
        }
        findViewById<Button>(R.id.button_ESDL).setOnClickListener {
            loadComments("ESDL")
        }
        findViewById<Button>(R.id.button_ESE).setOnClickListener {
            loadComments("ESE")
        }
        findViewById<Button>(R.id.button_ESS).setOnClickListener {
            loadComments("ESS")
        }
        findViewById<Button>(R.id.button_ESCE).setOnClickListener {
            loadComments("ESCE")
        }
    }

    private fun loadComments(school: String) {
        // Chama a função de filtro de acordo com a escola
        when (school) {
            "ESTG" -> commentViewModel.getCommentsFromESTG().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
            "ESA" -> commentViewModel.getCommentsFromESA().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
            "ESDL" -> commentViewModel.getCommentsFromESDL().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
            "ESE" -> commentViewModel.getCommentsFromESE().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
            "ESS" -> commentViewModel.getCommentsFromESS().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
            "ESCE" -> commentViewModel.getCommentsFromESCE().observe(this) { comments ->
                comments?.let { commentAdapter.submitList(it) }
            }
        }
    }
}
