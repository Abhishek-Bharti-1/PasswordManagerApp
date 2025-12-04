package com.example.passwordmanagerapplication.ui.screens.auth


import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerapplication.data.security.BiometricAuthenticator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val biometricAuthenticator: BiometricAuthenticator
) : ViewModel() {

    private val _authEvent = MutableSharedFlow<Boolean>()
    val authEvent = _authEvent

    fun authenticate(activity: FragmentActivity) {
        biometricAuthenticator.authenticate(
            activity,
            onSuccess = {
                viewModelScope.launch { _authEvent.emit(true) }
            },
            onError = {
                viewModelScope.launch { _authEvent.emit(false) }
            }
        )
    }
}