package com.example.challenge.sitrack.feature.main.domain

import com.example.challenge.sitrack.feature.main.data.remote.MainRemoteDataSource

class MainRepository(private val mainRemoteDataSource: MainRemoteDataSource) {

    suspend fun getRandomUser() = mainRemoteDataSource.getRandomUser()
}