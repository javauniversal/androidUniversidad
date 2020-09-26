package com.fiiss.feelingsanalysis.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product(idproductos: Int, nombre: String, descripcion: String, imagen: Int): Serializable {

    public var idproductos: Int? = null

    public var nombre: String? = null

    public var descripcion: String? = null

    public var imagen: Int? = null

    public var producto: Int? = null

    init {
        this.idproductos = idproductos
        this.nombre = nombre
        this.descripcion = descripcion
        this.imagen = imagen
    }

}