package com.pthw.codemanagement.utils.viewstate

sealed class ObjViewState<out T> {
    open operator fun invoke(): T? = null

    class Idle<out T> : ObjViewState<T>()
    class Loading<out T> : ObjViewState<T>()
    data class Success<out T>(val value: T) : ObjViewState<T>()
    data class Error<out T>(val errorMessage: String) : ObjViewState<T>()
}

fun <T> ObjViewState<T>.renderState(
    idle: () -> Unit = {},
    loading: () -> Unit = {},
    success: (data: T) -> Unit,
    error: (msg: String) -> Unit = {},
) {
    when (this) {
        is ObjViewState.Loading -> {
            loading.invoke()
        }
        is ObjViewState.Success -> {
            success.invoke(this.value)
        }
        is ObjViewState.Error -> {
            error.invoke(this.errorMessage)
        }
        else -> idle.invoke()
    }
}