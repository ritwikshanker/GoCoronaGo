package com.example.gocoronago.base

import kotlin.coroutines.CoroutineContext

interface IDispatcherProvider {
    val io: CoroutineContext
    val ui: CoroutineContext
}