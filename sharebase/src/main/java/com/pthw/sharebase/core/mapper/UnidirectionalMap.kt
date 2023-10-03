package com.pthw.sharebase.core.mapper

interface UnidirectionalMap<F, T> {
    fun map(item: F): T
}
