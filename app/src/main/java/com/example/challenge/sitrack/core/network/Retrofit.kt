package com.example.challenge.sitrack.core.network

import com.example.challenge.sitrack.core.services.Services
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .client(getClient())
        .addConverterFactory(getGsonConverterFactory())
        .build()

    private fun getClient() = OkHttpClient.Builder().apply {
        addInterceptor(getHttpLoggingInterceptor())
    }.build()

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HEADERS)
        setLevel(BASIC)
        setLevel(BODY)
    }

    private fun getGsonConverterFactory() = GsonConverterFactory.create()

    fun getRetrofit() = retrofit().create(Services::class.java)
}