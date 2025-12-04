package com.example.passwordmanagerapplication.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordmanagerapplication.data.local.dao.PasswordDao
import com.example.passwordmanagerapplication.data.local.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], version = 1, exportSchema = false)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao

    companion object {
        @Volatile private var instance: PasswordDatabase? = null

        fun getInstance(context: Context): PasswordDatabase = instance ?: synchronized(this) {
            val inst = Room.databaseBuilder(
                context.applicationContext,
                PasswordDatabase::class.java,
                "password_manager_db"
            ).build()
            instance = inst
            inst
        }
    }
}