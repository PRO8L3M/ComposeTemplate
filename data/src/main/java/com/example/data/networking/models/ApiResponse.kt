package com.example.data.networking.models

import com.example.domain.models.api.ApiError
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(
    val data: T?,
    val errors: List<ApiError>,
    val hasErrors: Boolean
)