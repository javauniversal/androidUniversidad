package com.fiiss.feelingsanalysis.models

import com.google.gson.annotations.SerializedName

class BaseResponse {

    @SerializedName("estado")
    public var estado: Boolean? = null

    @SerializedName("mensaje")
    public var mensaje: String? = null

    @SerializedName("datos")
    public var datos: String? = null

}
