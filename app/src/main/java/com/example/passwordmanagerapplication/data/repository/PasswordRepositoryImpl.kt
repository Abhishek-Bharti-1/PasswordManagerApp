package com.example.passwordmanagerapplication.data.repository

import com.example.passwordmanagerapplication.data.local.dao.PasswordDao
import com.example.passwordmanagerapplication.data.local.entity.PasswordEntity
import com.example.passwordmanagerapplication.data.security.EncryptionManager
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PasswordRepositoryImpl(
    private val dao: PasswordDao,
    private val encryptionManager: EncryptionManager
) : PasswordRepository {

    override fun getAllPasswords(): Flow<List<Password>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain(encryptionManager) }
        }
    }

    override suspend fun addPassword(password: Password) {
        dao.insert(password.toEntity(encryptionManager))
    }

    override suspend fun updatePassword(password: Password) {
        dao.update(password.toEntity(encryptionManager))
    }

    override suspend fun deletePassword(password: Password) {
        dao.delete(password.toEntity(encryptionManager))
    }

    private fun PasswordEntity.toDomain(encryptionManager: EncryptionManager): Password {
        return Password(
            id = id,
            accountType = accountType,
            username = encryptionManager.decrypt(username),
            password = encryptionManager.decrypt(passwordEncrypted)
        )
    }

    private fun Password.toEntity(encryptionManager: EncryptionManager): PasswordEntity {
        return PasswordEntity(
            id = id,
            accountType = accountType,
            username = encryptionManager.encrypt(username),
            passwordEncrypted = encryptionManager.encrypt(password)
        )
    }
}