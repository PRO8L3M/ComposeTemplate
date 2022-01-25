package com.example.domain.usecases.users

import com.example.domain.repositories.UsersRepository
import com.example.domain.usecases.base.UseCase
import javax.inject.Inject

class GetAvatarsUseCase @Inject constructor(
    private val repository: UsersRepository
) : UseCase<Unit, List<String>>() {

    override suspend fun run(params: Unit): List<String> = repository.getAvatars()
}