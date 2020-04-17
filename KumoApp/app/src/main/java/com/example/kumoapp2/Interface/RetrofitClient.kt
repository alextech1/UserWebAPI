package com.example.kumoapp2.Interface

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {
    private var instance: Retrofit? = null
    private const val BASE_URL = "http://192.168.198.1:5000/";

    fun getInstance(): Retrofit {
        if (instance == null)
            instance = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return instance!!
    }

    val webservice by lazy {
        Retrofit.Builder()
            getInstance().create(IMyAPI::class.java)
    }

}