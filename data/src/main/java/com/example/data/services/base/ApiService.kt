package com.example.data.services.base

import com.example.data.mappers.asHttpError
import com.example.data.mappers.asRestException
import com.example.data.networking.models.ApiResponse
import retrofit2.HttpException

abstract class ApiService {

    suspend fun <T> body(apiCall: suspend () -> T): T {
        return try {
            apiCall.invoke() ?: throw NullPointerException().asRestException()
        } catch (throwable: Throwable) {
            throw throwable.asRestException()
        }
    }

    suspend fun <T> request(apiCall: suspend () -> ApiResponse<T>): T {
        return try {
            apiCall.invoke().data ?: throw NullPointerException().asRestException()
        } catch (throwable: Throwable) {
            throw throwable.asRestException()
        }
    }

    suspend fun <T> requestApiResponse(apiCall: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return try {
            apiCall.invoke()
        } catch (throwable: HttpException) {
            ApiResponse(data = null, errors = throwable.asHttpError().errors, hasErrors = true)
        } catch (throwable: Throwable) {
            throw throwable.asRestException()
        }
    }
}