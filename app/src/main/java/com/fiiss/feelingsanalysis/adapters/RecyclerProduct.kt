package com.fiiss.feelingsanalysis.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.R
import com.fiiss.feelingsanalysis.models.Product


class RecyclerProduct(private val context: Activity, listProduct: List<Product>?) : RecyclerView.Adapter<ViewHolderProduct>() {

    private val listProduct: List<Product>? = listProduct

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduct {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolderProduct(view)
    }

    override fun onBindViewHolder(holder: ViewHolderProduct, @SuppressLint("RecyclerView") position: Int) {
        val product: Product = listProduct!![position]

        holder.nameProduct.text = product.nombre
        product.imagen?.let { holder.imgProduct.setImageResource(it) }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return if (listProduct == null) 0 else listProduct.size
    }

}