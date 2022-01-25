package com.example.data.repositories

import com.example.data.mappers.toApiModel
import com.example.data.mappers.toDomain
import com.example.data.services.UsersService
import com.example.domain.di.IODispatcher
import com.example.domain.models.users.User
import com.example.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultUsersRepository @Inject constructor(
    private val usersService: UsersService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : UsersRepository {

    override suspend fun getUsers(): List<User> {
        return withContext(dispatcher) {
            usersService.getUsers().toDomain()
        }
    }

    override suspend fun postUser(user: User) {
        return withContext(dispatcher) {
            usersService.postUser(user.toApiModel())
        }
    }

    override suspend fun deleteUser(id: Long) {
        return withContext(dispatcher) {
            usersService.deleteUser(id)
        }
    }

    override suspend fun getAvatars(): List<String> {
        return withContext(dispatcher) {
            usersService.getAvatarsUrls().data ?: emptyList()
        }
    }
}