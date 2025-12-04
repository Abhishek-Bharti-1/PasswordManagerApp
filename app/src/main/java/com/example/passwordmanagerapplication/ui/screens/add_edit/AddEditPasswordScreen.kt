package com.example.passwordmanagerapplication.ui.screens.add_edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.ui.components.PasswordInputField
import com.example.passwordmanagerapplication.ui.components.PrimaryButton
import com.example.passwordmanagerapplication.ui.components.StrengthMeter
import com.example.passwordmanagerapplication.utils.PasswordGenerator
import org.koin.androidx.compose.koinViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPasswordBottomSheet(
    onDismiss: () -> Unit,
    onSaved: () -> Unit,
    viewModel: AddEditPasswordViewModel = koinViewModel()
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    var account by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            OutlinedTextField(
                value = account,
                onValueChange = { account = it },
                label = { Text("Account Name", ) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = username,
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
            PrimaryButton(text = "Add New Account") {
                viewModel.savePassword(
                    Password(
                        accountType = account,
                        username = username,
                        password = password
                    ),
                    isEdit = false
                )
                onSaved()
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}