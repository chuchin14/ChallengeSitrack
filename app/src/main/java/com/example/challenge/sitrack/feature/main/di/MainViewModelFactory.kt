package com.example.challenge.sitrack.feature.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.sitrack.core.network.Retrofit
import com.example.challenge.sitrack.feature.main.data.remote.MainRemoteDataSource
import com.example.challenge.sitrack.feature.main.domain.GetRandomUserUseCase
import com.example.challenge.sitrack.feature.main.domain.MainRepository
import com.example.challenge.sitrack.feature.main.ui.MainViewModel

val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
    ): T {
        return MainViewModel(
            getRandomUserUseCase = GetRandomUserUseCase(
                mainRepository = MainRepository(
                    mainRemoteDataSource = MainRemoteDataSource(
                        services = Retrofit.getRetrofit()
                    )
                )
            )
        ) as T
    }
}