package com.example.domain.usecases.users

import com.example.domain.repositories.UsersRepository
import com.example.domain.usecases.base.UseCase
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UsersRepository
) : UseCase<Long, Unit>() {

    override suspend fun run(params: Long) = repository.deleteUser(params)
}