package com.example.gocoronago.ui.main

import com.example.gocoronago.homepage.models.WorldwideData
import retrofit2.http.GET

interface MainInterface {
    @GET("all")
    suspend fun getSummary(): WorldwideData
}