package com.example.gocoronago.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun createInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.covid19api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}