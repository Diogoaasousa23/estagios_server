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

    // Funções para obter comentários de escolas específicas, retornando LiveData

    @Query("SELECT * FROM comments WHERE school LIKE 'ESTG%'")
    fun getCommentsFromESTG(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE school LIKE 'ESA%'")
    fun getCommentsFromESA(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE school LIKE 'ESDL%'")
    fun getCommentsFromESDL(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE school LIKE 'ESE%'")
    fun getCommentsFromESE(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE school LIKE 'ESS%'")
    fun getCommentsFromESS(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE school LIKE 'ESCE%'")
    fun getCommentsFromESCE(): LiveData<List<Comment>>
}
