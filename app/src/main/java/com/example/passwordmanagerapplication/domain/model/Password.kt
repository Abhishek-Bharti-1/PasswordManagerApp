package com.example.passwordmanagerapplication.domain.model

data class Password(
    val id: Long = 0,
    val accountType: String,
    val username: String,
    val password: String
)
