package com.pthw.codemanagement.network.extension

import com.pthw.codemanagement.network.exception.NetworkException
import com.pthw.codemanagement.network.utils.DataResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.Response
import timber.log.Timber

/**
 * Get response from retrofit,
 * Return @NetworkException when error occur
 */
fun <T> Response<T>.getBody(): T = body() ?: throw NetworkException()

/**
 * Extract data from DataResponse generic
 * with moshi.
 */
inline fun <reified T> String.extractDataResponse(): T? {
    return try {
        val parameterizedType = Types.newParameterizedType(DataResponse::class.java, T::class.java)
        val adapter = Moshi.Builder().build().adapter<DataResponse<T>>(parameterizedType)
        adapter.fromJson(this)?.data
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}


