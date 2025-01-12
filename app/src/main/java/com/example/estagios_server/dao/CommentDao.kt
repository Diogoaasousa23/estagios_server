package com.example.estagios_server.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.estagios_server.entities.Comment

@Dao
interface CommentDao {

    // Função para inserir um comentário no banco de dados
    @Insert
    suspend fun insert(comment: Comment): Long  // Retorna o ID gerado para o comentário inserido

    // Função para obter todos os comentários
    @Query("SELECT * FROM comments")
    fun getAllComments(): LiveData<List<Comment>>  // Retorna LiveData para observação

    // Você pode adicionar mais métodos conforme necessário
}
