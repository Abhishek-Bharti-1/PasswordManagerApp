package com.example.passwordmanagerapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerapplication.domain.model.Password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordDetailsBottomSheet(
    password: Password,
    onEdit: (Password) -> Unit,
    onDelete: (Password) -> Unit,
    onDismiss: () -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            Text(
                text = "Account Details",
                fontSize = 18.sp,
                color = Color(0xFF2563EB),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(24.dp))

            DetailSection(label = "Account Name", value = password.accountType)

            DetailSection(label = "Username/ Email", value = password.username)

            Text(
                text = "Password",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = if (showPassword) password.password else "****",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff
                        else Icons.Default.Visibility,
                        contentDescription = "Toggle Password"
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEdit(password) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(text = "Edit", color = Color.White)
                }

                Spacer(Modifier.width(12.dp))
                Button(
                    onClick = { onDelete(password) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(text = "Delete", color = Color.White)
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DetailSection(label: String, value: String) {
    Text(text = label, fontSize = 13.sp, color = Color.Gray)
    Text(
        text = value,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 16.dp),
        style = TextStyle(fontWeight = FontWeight.Bold)
    )
}