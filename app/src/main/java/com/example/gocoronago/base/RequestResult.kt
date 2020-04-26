package com.example.gocoronago.base


sealed class RequestResult<out R> {

    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error<out T>(val throwable: Throwable) : RequestResult<T>()

    //    data class Loading<out T>(val any: Any) : Result<T>()
    data class Loading<out T>(val any: T) : RequestResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading[any=$any]"
        }
    }
}

/**
 * `true` if [RequestResult] is of type [Success] & holds non-null [Success.data].
 */
val RequestResult<*>.succeeded
    get() = this is RequestResult.Success && data != null