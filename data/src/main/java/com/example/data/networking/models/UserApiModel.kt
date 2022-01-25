package com.example.data.networking.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserApiModel(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val yearOfBirth: Int,
    val imageUrl: String
)