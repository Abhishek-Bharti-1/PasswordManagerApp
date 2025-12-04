package com.example.passwordmanagerapplication.domain.repository

import com.example.passwordmanagerapplication.data.local.entity.PasswordEntity
import com.example.passwordmanagerapplication.domain.model.Password
import kotlinx.coroutines.flow.Flow


interface PasswordRepository {
    fun getAllPasswords(): Flow<List<Password>>
    suspend fun addPassword(password: Password)
    suspend fun updatePassword(password: Password)
    suspend fun deletePassword(password: Password)
}