package com.example.passwordmanagerapplication.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.ui.screens.add_edit.AddEditPasswordViewModel
import com.example.passwordmanagerapplication.utils.PasswordGenerator
import org.koin.androidx.compose.koinViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPasswordBottomSheet(
    existingPassword: Password? = null,
    onDismiss: () -> Unit,
    onSaved: () -> Unit,
    viewModel: AddEditPasswordViewModel = koinViewModel()
) {
    var account by remember { mutableStateOf(existingPassword?.accountType ?: "") }
    var username by remember { mutableStateOf(existingPassword?.username ?: "") }
    var password by remember { mutableStateOf(existingPassword?.password ?: "") }

    val context = LocalContext.current

    // Listen for toast events
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val isEdit = existingPassword != null


//    var account by remember { mutableStateOf("") }
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
//            Text(if (isEdit) "Edit Account" else "Add New Account")

            OutlinedTextField(
                value = account,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.DarkGray,
                    unfocusedTextColor = Color.Gray,
                    focusedLabelColor = Color.Gray,        // Floating label when focused
                    unfocusedLabelColor = Color.Gray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                onValueChange = { account = it },
                label = { Text("Account Name", ) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = username,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.DarkGray,
                    unfocusedTextColor = Color.Gray,
                    focusedLabelColor = Color.Gray,        // Floating label when focused
                    unfocusedLabelColor = Color.Gray,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Gray
                ),
                onValueChange = { username = it },
                label = { Text("Username / Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            PasswordInputField(
                password = password,
                onPasswordChange = { password = it }
            )


            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Left side: Generate
                Text(
                    text = "Generate Password",
                    color = Color.Black,
                    fontSize = 14.sp,                      // small size
                    fontWeight = FontWeight.Bold,          // bold
                    textDecoration = TextDecoration.Underline,  // underline
                    modifier = Modifier
                        .clickable { password = PasswordGenerator.generate() }
                        .padding(end = 12.dp)
                )


                // Right side: Strength meter
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    StrengthMeter(password)
                }
            }
            Spacer(Modifier.height(12.dp))
            // Save Button
            PrimaryButton(text = if (isEdit) "Save Changes" else "Add Password") {
                when {
                    account.isBlank() -> {
                        viewModel.showError("Account Name cannot be empty")
                    }

                    username.isBlank() -> {
                        viewModel.showError("Username / Email cannot be empty")
                    }

                    password.isBlank() -> {
                        viewModel.showError("Password cannot be empty")
                    }

                    else -> {
                        viewModel.savePassword(
                            Password(
                                id = existingPassword?.id ?: 0,
                                accountType = account,
                                username = username,
                                password = password
                            ),
                            isEdit = false
                        )
                        onSaved()
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}


