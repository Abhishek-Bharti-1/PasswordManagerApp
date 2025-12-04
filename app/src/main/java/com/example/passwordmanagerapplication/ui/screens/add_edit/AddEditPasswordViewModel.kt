package com.example.passwordmanagerapplication.ui.screens.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.domain.usecase.AddPasswordUseCase
import com.example.passwordmanagerapplication.domain.usecase.UpdatePasswordUseCase
import kotlinx.coroutines.launch

class AddEditPasswordViewModel(
    private val addUseCase: AddPasswordUseCase,
    private val updateUseCase: UpdatePasswordUseCase
) : ViewModel() {

    fun savePassword(password: Password, isEdit: Boolean) {
        viewModelScope.launch {
            if (isEdit) updateUseCase(password)
            else addUseCase(password)
        }
    }
}