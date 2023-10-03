package com.pthw.codemanagement.utils.exceptionhandler

import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.pthw.codemanagement.R
import com.pthw.codemanagement.network.exception.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ExceptionToStringMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionToStringMapper {

    companion object {
        private const val ERROR_CODE_400 = 400
        private const val ERROR_CODE_401 = 401
        private const val ERROR_CODE_422 = 422
        private const val ERROR_CODE_403 = 403
        private const val ERROR_CODE_404 = 404
        private const val ERROR_CODE_500 = 500
        private const val ERROR_CODE_503 = 503
    }

    override fun getCode(item: Throwable): Int {
        return if (item is NetworkException) item.errorCode else 0
    }

    override fun getErrorBody(item: Throwable): String? {
        return if (item is NetworkException) item.errorBody else null
    }

    override fun map(item: Throwable): String {
        return when (item) {
            is UnknownHostException -> context.getString(R.string.error_no_internet)
            is SocketTimeoutException -> context.getString(R.string.error_socket_timeout)
            is ConnectException -> context.getString(R.string.error_no_internet)
            is NetworkException -> parseNetworkError(item)
            else -> {
                if (item.localizedMessage != null) {
                    if (item.localizedMessage.contains("No address associated with hostname", true))
                        context.getString(R.string.error_no_internet)
                    else item.localizedMessage
                } else context.getString(R.string.error_generic)
            }
        }

    }

    private fun parseNetworkError(exception: NetworkException): String {
        when (exception.errorCode) {
            ERROR_CODE_400 -> {
                return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                    ?: context.getString(R.string.error_generic)
            }

            ERROR_CODE_401 -> {
                return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                    ?: context.getString(R.string.error_server_401)
            }

            ERROR_CODE_503 -> {
                return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                    ?: context.getString(R.string.error_server_503)
            }

            ERROR_CODE_422 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(
                    R.string.error_generic
                )

            ERROR_CODE_403 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(
                    R.string.error_server_403
                )

            ERROR_CODE_404 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(R.string.error_server_404)

            ERROR_CODE_500 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(R.string.error_server_500)
        }

        return exception.errorBody?.let { parseMessageFromErrorBody(it) }
            ?: context.getString(
                R.string.error_generic
            )
    }

    /** Parse error message from error body */
    private fun parseMessageFromErrorBody(errorBody: String): String {
        Timber.e("error body in string : $errorBody")
        try {
            val errorBodyJson = JSONObject(errorBody)
            return errorBodyJson.getString("message")
        } catch (exception: Exception) {
            Timber.e(exception)
        }
        return context.getString(R.string.error_generic)
    }
}



