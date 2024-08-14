package com.example.challenge.sitrack.feature.main.data.remote

import com.example.challenge.sitrack.core.services.Services
import com.example.challenge.sitrack.feature.main.data.models.toRandomUserModel
import com.example.challenge.sitrack.feature.main.domain.models.RandomUserModel

class MainRemoteDataSource(private val services: Services) {

    suspend fun getRandomUser(): Result<RandomUserModel> = try {
        val result = services.getRandomUser()
        Result.success(result.toRandomUserModel())
    } catch (exception: Exception) {
        Result.failure(exception = exception)
    }
}