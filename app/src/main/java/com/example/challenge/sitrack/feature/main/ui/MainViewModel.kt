package com.example.challenge.sitrack.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.sitrack.feature.main.domain.GetRandomUserUseCase
import com.example.challenge.sitrack.feature.main.domain.models.RandomUserModel
import com.example.challenge.sitrack.feature.main.domain.models.toRandomUserUiModel
import com.example.challenge.sitrack.feature.main.ui.models.MainUiModel
import com.example.challenge.sitrack.feature.main.ui.models.RandomUserUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception

class MainViewModel(private val getRandomUserUseCase: GetRandomUserUseCase): ViewModel() {

    private val _mainUiState = MutableLiveData<MainUiModel>()

    val mainUiState: LiveData<MainUiModel>
        get() = _mainUiState

    fun getRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getRandomUserUseCase.getRandomUser()
            withContext(Dispatchers.Main) {
                when {
                    result.isSuccess -> result.onSuccess { onGetRandomUserSuccess(it) }
                    result.isFailure -> result.onFailure { onGetRandomUserError(exception = Exception(it.message)) }
                }
            }
        }
    }

    private fun onGetRandomUserSuccess(randomUserModel: RandomUserModel) {
        emitMainUiState(randomUserUiModel = randomUserModel.toRandomUserUiModel())
    }

    private fun onGetRandomUserError(exception: Exception) {
        exception.printStackTrace()
        emitMainUiState(exception = exception)
    }

    private fun emitMainUiState(showRefresh: Boolean = false, randomUserUiModel: RandomUserUiModel? = null, exception: Exception? = null ) {
        _mainUiState.value = MainUiModel(showRefresh = showRefresh, randomUserUiModel = randomUserUiModel, exception = exception)
    }
}