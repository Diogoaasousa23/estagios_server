package com.example.estagios_server.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.estagios_server.dao.CommentDao
import com.example.estagios_server.entities.Comment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentRoomDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao

    private class CommentDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.commentDao())
                }
            }
        }

        suspend fun populateDatabase(commentDao: CommentDao) {
            // Popula a base de dados com exemplos iniciais
            commentDao.insert(Comment(1, "Ótima escola!", "ESE"))
            commentDao.insert(Comment(2, "Excelentes professores.", "ESA"))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CommentRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CommentRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CommentRoomDatabase::class.java,
                    "comment_database"
                )
                    .addCallback(CommentDatabaseCallback(scope)) // Callback para preencher dados iniciais
                    .fallbackToDestructiveMigration() // Migração destrutiva
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
