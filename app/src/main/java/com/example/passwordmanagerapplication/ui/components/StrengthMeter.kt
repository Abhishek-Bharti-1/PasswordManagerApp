package com.example.passwordmanagerapplication.ui.components


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerapplication.utils.PasswordStrengthChecker

@Composable
fun StrengthMeter(password: String) {
    val level = PasswordStrengthChecker.checkStrength(password)

    Text(
        text = if(password == "") "" else "Strength: $level",
        fontSize = 14.sp,
        color = when (level) {
            "Weak" -> Color.Red
            "Medium" -> Color(0xFFFFA500)
            else -> Color.Green
        }
    )
}