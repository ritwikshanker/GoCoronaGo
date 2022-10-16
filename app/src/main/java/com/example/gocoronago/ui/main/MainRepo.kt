package com.example.gocoronago.ui.main

import com.example.gocoronago.base.BaseRepo
import com.example.gocoronago.homepage.models.WorldwideData

class MainRepo : BaseRepo() {

    private val service = retrofit.create(MainInterface::class.java)
    suspend fun getCovidSummary(): WorldwideData {
        return service.getSummary()
    }
}