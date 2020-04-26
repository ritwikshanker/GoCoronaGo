package com.example.gocoronago.ui.main

import android.content.Context
import com.example.gocoronago.HomePage.Summary
import com.example.gocoronago.base.BaseRepo

class MainRepo() : BaseRepo() {

    private val service = retrofit.create(MainInterface::class.java)
    suspend fun getCovidSummary(): Summary {
        return service.getSummary()
    }
}