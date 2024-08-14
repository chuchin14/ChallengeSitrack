package com.example.challenge.sitrack.feature.main.ui.models

import com.google.android.gms.maps.model.LatLng
import java.lang.Exception

data class MainUiModel(
    val showRefresh: Boolean,
    val randomUserUiModel: RandomUserUiModel?,
    val exception: Exception?
)

data class RandomUserUiModel(
    val gender: String,
    val fullName: String,
    val pictureLarge: String,
    val email: String,
    val position: LatLng,
    val fullLocation: String)