package com.example.domain.models.api

sealed class ApiException private constructor(
    override val message: String?,
    exception: Throwable
) : RuntimeException(message, exception) {

    class Http(
        message: String?,
        exception: Throwable,
        val errors: List<ApiError> = emptyList()
    ) : ApiException(message, exception)

    class Network(message: String?, exception: Throwable) : ApiException(message, exception)

    class Unexpected(message: String?, exception: Throwable) : ApiException(message, exception)
}