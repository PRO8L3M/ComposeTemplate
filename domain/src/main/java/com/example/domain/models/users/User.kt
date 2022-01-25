package com.example.domain.models.users

import java.util.*

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val yearOfBirth: Int,
    val imageUrl: String
) {
    companion object {
        fun buildDefault() = User(
            UUID.randomUUID().leastSignificantBits,
            "No",
            "Data",
            Calendar.getInstance().get(Calendar.YEAR),
            "no-data"
        )
    }
}