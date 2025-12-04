package com.example.passwordmanagerapplication.utils

import kotlin.random.Random

object PasswordGenerator {
    fun generate(length: Int = 12): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#\$%"
        return (1..length).map { chars[Random.nextInt(chars.length)] }.joinToString("")
    }
}