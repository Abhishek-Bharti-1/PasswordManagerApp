package com.example.passwordmanagerapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val accountType: String,
    val username: String,
    val passwordEncrypted: String,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
