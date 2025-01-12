package com.example.estagios_server.repository

import androidx.lifecycle.LiveData
import com.example.estagios_server.dao.CommentDao
import com.example.estagios_server.entities.Comment

class CommentRepository(private val commentDao: CommentDao) {

    // Função para obter todos os comentários
    fun getAllComments(): LiveData<List<Comment>> {
        return commentDao.getAllComments() // Retorna LiveData
    }

    // Função para inserir comentário
    suspend fun insert(comment: Comment) {
        commentDao.insert(comment)
    }
}
