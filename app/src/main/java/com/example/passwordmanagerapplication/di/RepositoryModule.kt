package com.example.passwordmanagerapplication.di


import com.example.passwordmanagerapplication.data.repository.PasswordRepositoryImpl
import com.example.passwordmanagerapplication.data.security.EncryptionManager
import com.example.passwordmanagerapplication.domain.repository.PasswordRepository
import org.koin.dsl.module


/**
 * Binds the concrete implementation to the domain repository interface.
 */
val repositoryModule = module {

    single<PasswordRepository> { PasswordRepositoryImpl(get(), EncryptionManager) }

}