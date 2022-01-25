package com.example.domain.usecases.users

import com.example.domain.models.users.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.usecases.base.UseCase
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UsersRepository
) : UseCase<User, Unit>() {

    override suspend fun run(params: User) = repository.postUser(params)
}