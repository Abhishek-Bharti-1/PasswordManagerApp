package com.example.passwordmanagerapplication.domain.usecase

import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.domain.repository.PasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddPasswordUseCase (
    private val repository: PasswordRepository
) {
    suspend operator fun invoke(password: Password) {
        withContext(Dispatchers.IO) {   // ✅ Forces background thread
            repository.addPassword(password)
        }
    }
}