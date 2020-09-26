package com.fiiss.feelingsanalysis.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.R
import com.fiiss.feelingsanalysis.models.Historial
import com.fiiss.feelingsanalysis.models.Product


class RecyclerCalificacion(listHistorial: List<Historial>?) : RecyclerView.Adapter<ViewHolderCalificacion>() {

    private val listHistorial: List<Historial>? = listHistorial

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCalificacion {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_calificacion, parent, false)
        return ViewHolderCalificacion(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCalificacion, @SuppressLint("RecyclerView") position: Int) {
        val historial: Historial = listHistorial!![position]
        holder.texto.text = "Mesaje: "+historial.texto
        holder.raw_class.text = "Class: "+historial.raw_class
        holder.raw_score.text = "Score: "+historial.raw_score
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return listHistorial?.size ?: 0
    }

}