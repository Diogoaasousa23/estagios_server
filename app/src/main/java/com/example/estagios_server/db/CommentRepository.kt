package com.example.estagios_server.repository

import androidx.lifecycle.LiveData
import com.example.estagios_server.dao.CommentDao
import com.example.estagios_server.entities.Comment

class CommentRepository(private val commentDao: CommentDao) {

    // Função para obter todos os comentários
    fun getAllComments(): LiveData<List<Comment>> {
        return commentDao.getAllComments()
    }

    // Funções para obter comentários de escolas específicas
    fun getCommentsFromESTG(): LiveData<List<Comment>> = commentDao.getCommentsFromESTG()
    fun getCommentsFromESA(): LiveData<List<Comment>> = commentDao.getCommentsFromESA()
    fun getCommentsFromESDL(): LiveData<List<Comment>> = commentDao.getCommentsFromESDL()
    fun getCommentsFromESE(): LiveData<List<Comment>> = commentDao.getCommentsFromESE()
    fun getCommentsFromESS(): LiveData<List<Comment>> = commentDao.getCommentsFromESS()
    fun getCommentsFromESCE(): LiveData<List<Comment>> = commentDao.getCommentsFromESCE()

    // Função para inserir comentário
    suspend fun insert(comment: Comment) {
        commentDao.insert(comment)
    }
}
