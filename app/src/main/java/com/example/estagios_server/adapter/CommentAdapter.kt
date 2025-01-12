package com.example.estagios_server.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.estagios_server.R
import com.example.estagios_server.entities.Comment

class CommentAdapter : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentTextView: TextView = itemView.findViewById(R.id.commentText)
        private val schoolTextView: TextView = itemView.findViewById(R.id.schoolText)

        fun bind(item: Comment) {
            commentTextView.text = item.comment ?: "Comentário indisponível"
            schoolTextView.text = item.school ?: "Escola desconhecida"
        }

        companion object {
            fun create(parent: ViewGroup): CommentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_comment, parent, false)
                return CommentViewHolder(view)
            }
        }
    }

    class CommentsComparator : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }
}
