package com.example.estagios_server

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comment)

        // Inicializando o RecyclerView e o Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_comments)
        val adapter = CommentAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa todos os comentários (LiveData)
        commentViewModel.getAllComments().observe(this) { comments ->
            // Atualiza a lista de comentários quando os dados mudam
            comments?.let {
                adapter.submitList(it)
            }
        }
    }
}
