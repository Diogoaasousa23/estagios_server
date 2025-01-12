package com.example.estagios_server.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.estagios_server.entities.Comment
import com.example.estagios_server.repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(private val repository: CommentRepository) : ViewModel() {

    // Função para inserir comentário
    fun insert(comment: Comment) = viewModelScope.launch {
        repository.insert(comment)
    }

    // Função para obter todos os comentários
    fun getAllComments(): LiveData<List<Comment>> = repository.getAllComments()

    // Funções para obter comentários de escolas específicas
    fun getCommentsFromESTG(): LiveData<List<Comment>> = repository.getCommentsFromESTG()
    fun getCommentsFromESA(): LiveData<List<Comment>> = repository.getCommentsFromESA()
    fun getCommentsFromESDL(): LiveData<List<Comment>> = repository.getCommentsFromESDL()
    fun getCommentsFromESE(): LiveData<List<Comment>> = repository.getCommentsFromESE()
    fun getCommentsFromESS(): LiveData<List<Comment>> = repository.getCommentsFromESS()
    fun getCommentsFromESCE(): LiveData<List<Comment>> = repository.getCommentsFromESCE()
}

class CommentViewModelFactory(private val repository: CommentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
