package com.fiiss.feelingsanalysis.models

class Parametros2(nombreArchivo: String, urlArchivo: String, idProducto: String) {

    public var nombreArchivo: String? = null

    public var urlArchivo: String? = null

    public var idProducto: String? = null

    init {
        this.nombreArchivo = nombreArchivo
        this.urlArchivo = urlArchivo
        this.idProducto = idProducto
    }

}