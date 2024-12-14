package com.example.estagios_server

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class CursoAdapter(context: Context, cursos: List<Curso>) :
    ArrayAdapter<Curso>(context, 0, cursos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val curso = getItem(position)
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_curso_adapter, parent, false)
        val nomeCurso: TextView = view.findViewById(R.id.nomeCurso)
        nomeCurso.text = curso?.nome

        // Ajustando o comportamento do TextView para múltiplas linhas
        nomeCurso.ellipsize = TextUtils.TruncateAt.END // Truncar texto se exceder uma linha
        nomeCurso.maxLines = 2 // Permitir duas linhas
        nomeCurso.isSingleLine = false // Permitir várias linhas

        // Alterando a cor de fundo para branco
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

        return view
    }
}
