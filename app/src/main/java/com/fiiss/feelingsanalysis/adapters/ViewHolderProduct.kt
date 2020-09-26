package com.fiiss.feelingsanalysis.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.R

class ViewHolderProduct internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imgProduct: ImageView
    var nameProduct: TextView

    init {
        imgProduct = itemView.findViewById(R.id.imgProduct)
        nameProduct = itemView.findViewById(R.id.nameProduct)
    }
}