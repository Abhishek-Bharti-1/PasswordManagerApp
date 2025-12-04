package com.example.passwordmanagerapplication



import android.app.Application
import com.example.passwordmanagerapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PasswordManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PasswordManagerApplication)
            modules(appModule)
        }
    }
}
