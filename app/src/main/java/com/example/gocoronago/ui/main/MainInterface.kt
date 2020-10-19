package com.example.gocoronago.ui.main

import com.example.gocoronago.homepage.Summary
import retrofit2.http.GET

interface MainInterface {
    @GET("summary")
    suspend fun getSummary(): Summary
}