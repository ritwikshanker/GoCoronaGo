package com.example.gocoronago.base

import kotlinx.coroutines.Dispatchers

object ProductionDispatcherProvider: IDispatcherProvider {
    override val io = Dispatchers.IO
    override val ui = Dispatchers.Main
}