package com.pthw.codemanagement.utils.exceptionhandler

import com.pthw.sharebase.core.mapper.UnidirectionalMap


interface ExceptionToStringMapper : UnidirectionalMap<Throwable, String> {
    fun getCode(item: Throwable): Int
    fun getErrorBody(item: Throwable): String?
}
