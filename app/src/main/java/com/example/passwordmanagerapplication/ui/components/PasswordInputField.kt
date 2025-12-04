package com.example.passwordmanagerapplication.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lint.kotlin.metadata.Visibility


@Composable
fun PasswordInputField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Password") },
        singleLine = true,

        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = Color.Gray,
            focusedLabelColor = Color.Gray,        // Floating label when focused
            unfocusedLabelColor = Color.Gray,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Gray
        ),

        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),

        trailingIcon = {
            val icon = if (passwordVisible)
                Icons.Default.Visibility
            else
                Icons.Default.VisibilityOff

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = icon,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        }
    )
}