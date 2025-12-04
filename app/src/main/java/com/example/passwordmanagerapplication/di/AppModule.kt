package com.example.passwordmanagerapplication.di

import android.content.Context
import com.example.passwordmanagerapplication.data.local.database.PasswordDatabase
import com.example.passwordmanagerapplication.data.repository.PasswordRepositoryImpl
import com.example.passwordmanagerapplication.data.security.EncryptionManager
import com.example.passwordmanagerapplication.domain.repository.PasswordRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import androidx.room.Room
import com.example.passwordmanagerapplication.domain.usecase.AddPasswordUseCase
import com.example.passwordmanagerapplication.domain.usecase.DeletePasswordUseCase
import com.example.passwordmanagerapplication.domain.usecase.GetPasswordsUseCase
import com.example.passwordmanagerapplication.domain.usecase.UpdatePasswordUseCase
import com.example.passwordmanagerapplication.ui.screens.add_edit.AddEditPasswordViewModel
import com.example.passwordmanagerapplication.ui.screens.home.HomeViewModel

val appModule = module {

    // DATABASE
    single {
        Room.databaseBuilder(
            androidContext(),
            PasswordDatabase::class.java,
            "password_db"
        ).build()
    }

    // DAO
    single { get<PasswordDatabase>().passwordDao() }

    // ENCRYPTION
    single { EncryptionManager }

    // REPOSITORY
    single<PasswordRepository> {
        PasswordRepositoryImpl(
            dao = get(),
            encryptionManager = get()
        )
    }

    // USECASES
    factory { AddPasswordUseCase(get()) }
    factory { UpdatePasswordUseCase(get()) }
    factory { DeletePasswordUseCase(get()) }
    factory { GetPasswordsUseCase(get()) }

    // VIEWMODELS
    factory {
        HomeViewModel(
            getPasswordsUseCase = get(),
            deletePasswordUseCase = get()
        )
    }

    factory {
        AddEditPasswordViewModel(
            addUseCase = get(),
            updateUseCase = get()
        )
    }
}