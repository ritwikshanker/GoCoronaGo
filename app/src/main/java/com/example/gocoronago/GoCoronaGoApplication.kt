package com.example.gocoronago

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Interceptor

@HiltAndroidApp
class GoCoronaGoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPlugins()
    }

    companion object {
        var interceptor: Interceptor? = null
    }

    private fun initPlugins() {
        interceptor = FlipperInitializer.initFlipperPlugins(this)
    }
}