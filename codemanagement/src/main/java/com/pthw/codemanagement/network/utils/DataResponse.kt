package com.pthw.codemanagement.network.utils

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse<T>(
    @Json(name = "page") val currentPage: Int?,
    @Json(name = "total_pages") val totalPage: Int?,
    @Json(name = "total_results") val totalCount: Int?,
    @Json(name = "results") val data: T?,
)

@JsonClass(generateAdapter = true)
data class DataEmptyResponse(
    @Json(name = "message") val message: String?
)
