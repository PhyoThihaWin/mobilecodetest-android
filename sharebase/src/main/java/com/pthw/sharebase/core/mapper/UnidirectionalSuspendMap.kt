package com.pthw.sharebase.core.mapper

interface UnidirectionalSuspendMap<F, T> {
    suspend fun map(item: F): T
}

