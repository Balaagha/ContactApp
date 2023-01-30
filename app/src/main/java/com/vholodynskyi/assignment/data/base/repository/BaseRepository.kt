package com.vholodynskyi.assignment.data.base.repository

import android.util.Log
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.base.models.FailureType
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepository {

    inline fun <reified T> safeApiCall(
        apiCall: () -> Response<T>
    ): DataWrapper<Response<T>> {
        return try {
            val response = apiCall.invoke()
            DataWrapper.Success(
                value = response
            )
        } catch (throwable: Throwable) {
            Log.d("myTag","ERROR => ${throwable.localizedMessage}")
            handleApiCallException(throwable)
        }
    }

    fun <T> handleApiCallException(throwable: Throwable): DataWrapper<T> {
        when (throwable) {
            is SocketTimeoutException -> {
                return DataWrapper.Failure(FailureType.RESPONSE_TIMEOUT)
            }
            is UnknownHostException -> {
                return DataWrapper.Failure(FailureType.NO_INTERNET_CONNECTION)
            }

            is ConnectException -> {
                return DataWrapper.Failure(FailureType.NO_INTERNET_CONNECTION)
            }
            is HttpException -> {
                return if (throwable.response()?.errorBody()!!.charStream().readText()
                        .isEmpty()
                ) {
                    DataWrapper.Failure(
                        failureType = FailureType.HTTP_EXCEPTION,
                        code = throwable.code()
                    )
                } else {
                    DataWrapper.Failure(
                        failureType = FailureType.HTTP_EXCEPTION,
                        code = throwable.code()
                    )
                }
            }
            else -> {
                return DataWrapper.Failure(FailureType.OTHER)
            }
        }
    }

}