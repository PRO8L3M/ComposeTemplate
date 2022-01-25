package com.example.data.services.api

import com.example.data.networking.endpoints.UsersEndpoints
import com.example.data.networking.models.ApiResponse
import com.example.data.networking.models.UserApiModel
import com.example.data.services.UsersService
import com.example.data.services.base.ApiService
import javax.inject.Inject

class DefaultUsersService @Inject constructor(
    private val usersEndpoints: UsersEndpoints
) : ApiService(), UsersService {

    override suspend fun getUsers(): List<UserApiModel> = body { usersEndpoints.getUsers() }

    override suspend fun postUser(user: UserApiModel): ApiResponse<Unit> = requestApiResponse {
        usersEndpoints.postUser(user)
    }

    override suspend fun deleteUser(id: Long): ApiResponse<Unit> = requestApiResponse {
        usersEndpoints.deleteUser(id)
    }

    override suspend fun getAvatarsUrls(): ApiResponse<List<String>> = requestApiResponse {
        usersEndpoints.getAvatarsUrls()
    }
}