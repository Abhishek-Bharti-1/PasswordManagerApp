package com.example.passwordmanagerapplication.data.repository




import com.example.passwordmanagerapplication.data.local.entity.PasswordEntity
import com.example.passwordmanagerapplication.data.security.EncryptionManager
import com.example.passwordmanagerapplication.domain.model.Password

/**
 * Map a Room PasswordEntity (encrypted) to a domain Password (decrypted).
 * Decryption errors are caught and the password becomes an empty string or masked value.
 */
fun PasswordEntity.toDomain(): Password {
    val plain = try {
        EncryptionManager.decrypt(this.passwordEncrypted)
    } catch (e: Exception) {
        // On decryption failure return masked/empty password to avoid crash
        ""
    }

    return Password(
        id = this.id,
        accountType = this.accountType,
        username = this.username,
        password = plain,
//        notes = this.notes,
//        createdAt = this.createdAt
    )
}

/**
 * Map domain Password back to PasswordEntity.
 * This function encrypts the domain.password so the entity stores encrypted data.
 */
fun Password.toEntity(): PasswordEntity {
    val encrypted = try {
        EncryptionManager.encrypt(this.password)
    } catch (e: Exception) {
        // If encryption fails, store empty string (caller should handle failure)
        ""
    }

    return PasswordEntity(
        id = this.id,
        accountType = this.accountType,
        username = this.username,
        passwordEncrypted = encrypted,
//        notes = this.notes,
//        createdAt = this.createdAt
    )
}
