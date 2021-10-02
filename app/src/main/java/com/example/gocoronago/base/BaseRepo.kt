package com.example.gocoronago.base

import com.example.gocoronago.GoCoronaGoApplication
import kotlinx.coroutines.Dispatchers

open class BaseRepo {
    var retrofit = RetrofitInstance.createInstance(GoCoronaGoApplication.interceptor)
    private val ioDispatcher = Dispatchers.IO
}