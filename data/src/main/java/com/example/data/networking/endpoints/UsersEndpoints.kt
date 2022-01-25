package com.example.data.networking.endpoints

import com.example.data.networking.models.ApiResponse
import com.example.data.networking.models.UserApiModel
import retrofit2.http.*

interface UsersEndpoints {

    @GET("/users")
    suspend fun getUsers(): List<UserApiModel>

    @POST("/user")
    suspend fun postUser(@Body user: UserApiModel): ApiResponse<Unit>

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path("id") id: Long): ApiResponse<Unit>

    @GET("/avatars")
    suspend fun getAvatarsUrls(): ApiResponse<List<String>>
}