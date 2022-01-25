package com.example.domain.usecases.users

import com.example.domain.models.users.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.usecases.base.UseCase
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) : UseCase<Unit, List<User>>() {

    override suspend fun run(params: Unit): List<User> = repository.getUsers()
}