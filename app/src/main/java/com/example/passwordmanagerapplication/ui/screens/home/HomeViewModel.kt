package com.example.passwordmanagerapplication.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.domain.usecase.DeletePasswordUseCase
import com.example.passwordmanagerapplication.domain.usecase.GetPasswordsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class HomeViewModel(
    getPasswordsUseCase: GetPasswordsUseCase,
    private val deletePasswordUseCase: DeletePasswordUseCase
) : ViewModel() {

    val passwords: StateFlow<List<Password>> =
        getPasswordsUseCase()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )


    fun deletePassword(password: Password) {
        viewModelScope.launch {
            deletePasswordUseCase(password)
        }
    }
}