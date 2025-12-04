package com.example.passwordmanagerapplication.utils

object PasswordStrengthChecker {
    fun checkStrength(password: String): String {
        return when {
            password.length < 6 -> "Weak"
            password.any(Char::isDigit) && password.any(Char::isUpperCase) -> "Strong"
            else -> "Medium"
        }
    }
}