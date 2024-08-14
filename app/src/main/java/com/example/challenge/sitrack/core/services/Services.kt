package com.example.challenge.sitrack.core.services

import com.example.challenge.sitrack.feature.main.data.models.GetRandomUserApiResponse
import retrofit2.http.GET

interface Services {

    @GET("/api/")
    suspend fun getRandomUser(): GetRandomUserApiResponse
}