package com.example.data.services

import com.example.data.networking.models.ApiResponse
import com.example.data.networking.models.UserApiModel

interface UsersService {

    suspend fun getUsers(): List<UserApiModel>
    suspend fun postUser(user: UserApiModel): ApiResponse<Unit>
    suspend fun deleteUser(id: Long): ApiResponse<Unit>

    suspend fun getAvatarsUrls(): ApiResponse<List<String>>
}