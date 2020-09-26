package com.fiiss.feelingsanalysis

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppAndroidUnit: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AppAndroidUnit? = null
        private var retrofit: Retrofit? = null
        private var retrofit2: Retrofit? = null
        lateinit  var appContext: Context


        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun retrofitCliente() : Retrofit? {
            return retrofit
        }

        fun retrofitCliente2() : Retrofit? {
            return retrofit2
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initialRetrofit()
        initialRetrofit2()
    }

    private fun initialRetrofit() {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl("https://appdomidomi.com/backend_domidomi_cienaga/public/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun initialRetrofit2() {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        retrofit2 = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}