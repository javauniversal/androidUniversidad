package com.fiiss.feelingsanalysis.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.R

class ViewHolderCalificacion internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var raw_class: TextView
    var raw_score: TextView
    var texto: TextView

    init {
        raw_score = itemView.findViewById(R.id.raw_score)
        texto = itemView.findViewById(R.id.texto)
        raw_class = itemView.findViewById(R.id.raw_class)
    }
}