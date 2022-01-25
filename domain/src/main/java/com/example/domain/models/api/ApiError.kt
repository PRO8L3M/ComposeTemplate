package com.example.domain.models.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    val code: String,
    val message: String
)