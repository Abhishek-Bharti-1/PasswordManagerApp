package com.example.passwordmanagerapplication.domain.usecase

import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow

class GetPasswordsUseCase(
    private val repository: PasswordRepository
) {
    operator fun invoke(): Flow<List<Password>> {
        return repository.getAllPasswords()
    }
}