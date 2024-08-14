package com.example.challenge.sitrack.feature.main.domain

class GetRandomUserUseCase(private val mainRepository: MainRepository) {

    suspend fun getRandomUser() = mainRepository.getRandomUser()
}