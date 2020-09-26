package com.fiiss.feelingsanalysis.adapters

import com.fiiss.feelingsanalysis.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServiceRestApp {

    @Headers("Content-Type: application/json")
    @POST("subirArchivos")
    fun subirArchivo(@Body usuarioApp: Parametros?): Call<BaseResponse?>?

    @Headers("Content-Type: application/json")
    @POST("save_record")
    fun procesoGuardado(@Body parametros: Parametros2?): Call<BaseResponse?>?

    @Headers("Content-Type: application/json")
    @POST("listar")
    fun listar(@Body product: Product?): Call<List<Historial>?>?

}