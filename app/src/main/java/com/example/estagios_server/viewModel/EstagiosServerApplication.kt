package com.example.estagios_server

import android.app.Application
import com.example.estagios_server.db.CommentRoomDatabase
import com.example.estagios_server.repository.CommentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EstagiosServerApplication : Application() {
    // Não é necessário cancelar este escopo, pois ele será destruído com o processo
    val applicationScope = CoroutineScope(SupervisorJob())

    // Usando by lazy para garantir que o banco de dados e o repositório sejam criados apenas quando necessários
    // ao invés de quando a aplicação começar
    val database by lazy { CommentRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CommentRepository(database.commentDao()) }
}
