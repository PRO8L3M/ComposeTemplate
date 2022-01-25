package com.example.data.mappers

import com.example.data.networking.models.UserApiModel
import com.example.domain.models.users.User

fun UserApiModel.toDomain() = User(id, firstName, lastName, yearOfBirth, imageUrl)

fun List<UserApiModel>.toDomain() = map { it.toDomain() }

fun User.toApiModel() = UserApiModel(id, firstName, lastName, yearOfBirth, imageUrl)