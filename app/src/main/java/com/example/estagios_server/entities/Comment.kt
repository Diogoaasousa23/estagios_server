package com.example.estagios_server.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments") // Nome da tabela no banco de dados
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // ID auto-gerado pelo Room
    val comment: String?,
    val school: String?
)
