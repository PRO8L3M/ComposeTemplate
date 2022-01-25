package com.example.domain.repositories

import com.example.domain.models.users.User

interface UsersRepository {

    suspend fun getUsers(): List<User>
    suspend fun postUser(user: User)
    suspend fun deleteUser(id: Long)

    suspend fun getAvatars(): List<String>
}