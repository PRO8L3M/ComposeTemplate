package com.example.data.mappers

import com.example.data.networking.models.ApiResponse
import com.example.domain.models.api.ApiError
import com.example.domain.models.api.ApiException
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.HttpException
import java.io.IOException

fun Throwable.asRestException(): ApiException {
    return when (this) {
        is HttpException -> asHttpError()
        is IOException -> asNetworkError()
        else -> asUnexpectedError()
    }
}

fun HttpException.asHttpError(): ApiException.Http {
    return ApiException.Http(
        message,
        this,
        getErrorList(this)
    )
}

private fun IOException.asNetworkError(): ApiException.Network {
    return ApiException.Network(
        message,
        this
    )
}

private fun Throwable.asUnexpectedError(): ApiException.Unexpected {
    return ApiException.Unexpected(
        message,
        this
    )
}

private fun getErrorList(httpException: HttpException): List<ApiError> {
    val errorBody = httpException.response()?.errorBody()?.string() ?: return emptyList()
    val response = getRestResponseFromBody(errorBody)
    return response?.errors ?: emptyList()
}

@Suppress("SwallowedException")
private fun getRestResponseFromBody(responseBody: String): ApiResponse<*>? {
    return try {
        val moshi = Moshi.Builder().build()
        val parameterizedType = Types.newParameterizedType(ApiResponse::class.java, Any::class.java)
        val adapter = moshi.adapter<ApiResponse<*>>(parameterizedType).lenient()
        adapter.fromJson(responseBody)
    } catch (exception: JsonDataException) {
        ApiResponse(null, emptyList(), true)
    }
}
