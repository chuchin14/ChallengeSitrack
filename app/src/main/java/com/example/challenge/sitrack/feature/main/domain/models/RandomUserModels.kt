package com.example.challenge.sitrack.feature.main.domain.models

import com.example.challenge.sitrack.feature.main.ui.models.RandomUserUiModel
import com.google.android.gms.maps.model.LatLng

data class RandomUserModel(
    val title: String,
    val gender: String,
    val name: String,
    val lastname: String,
    val streetName: String,
    val streetNumber: Int,
    val city: String,
    val state: String,
    val country: String,
    val postCode: String,
    val pictureLarge: String,
    val latitude: Double,
    val longitude: Double,
    val email: String,
)

fun RandomUserModel.toRandomUserUiModel() = RandomUserUiModel(
    gender = gender,
    pictureLarge = pictureLarge,
    position =  LatLng(latitude, longitude),
    fullName = "$title $name $lastname",
    fullLocation = "$streetName $streetNumber, $city, $state, $country, $postCode",
    email = email,
)