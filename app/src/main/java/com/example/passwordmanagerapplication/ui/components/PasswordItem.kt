package com.example.passwordmanagerapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerapplication.domain.model.Password

@Composable
fun PasswordItem(
    password: Password,
    onClick: () -> Unit
) {
    val masked = "********"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF3F4F6)) // light grey like image
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Left: Account name
        Text(
            text = password.accountType,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            color = Color.Black,
            modifier = Modifier.weight(1f) // pushes password + arrow to right
        )

        // Middle: Masked password
        Text(
            text = masked,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(end = 12.dp)
        )

        // Right: Arrow icon
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Open",
            tint = Color.Gray
        )
    }
}