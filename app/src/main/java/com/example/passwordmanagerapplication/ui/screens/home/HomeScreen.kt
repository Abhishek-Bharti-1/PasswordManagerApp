package com.example.passwordmanagerapplication.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.passwordmanagerapplication.domain.model.Password
import com.example.passwordmanagerapplication.ui.components.AddEditPasswordBottomSheet
import com.example.passwordmanagerapplication.ui.components.PasswordDetailsBottomSheet
import com.example.passwordmanagerapplication.ui.components.PasswordItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    var showAddSheet by remember { mutableStateOf(false) }
    var showViewPasswordSheet by remember { mutableStateOf(false) }
    val passwords by viewModel.passwords.collectAsState()
    var selectedPassword by remember { mutableStateOf<Password?>(null) }
    var isEdit by remember { mutableStateOf(false) }

    if (showViewPasswordSheet && selectedPassword != null) {
        PasswordDetailsBottomSheet(
            password = selectedPassword!!,
            onEdit = { pwd ->
                showViewPasswordSheet = false
                isEdit = true
                selectedPassword = pwd
                showAddSheet = true
            },
            onDelete = {
                viewModel.deletePassword(it)
                showViewPasswordSheet = false
                selectedPassword = null
            },
            onDismiss = { showViewPasswordSheet = false
            selectedPassword = null}
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Password Manager",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3F4F6)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedPassword = null
                showAddSheet = true
            }) {     // ✅ Floating Add Button
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Password",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F4F6))     // ✅ Extra safety
                .padding(innerPadding)
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(passwords) { password ->
                    PasswordItem(
                        password = password,
                        onClick = {
                            selectedPassword = password
                            showViewPasswordSheet = true
                        }
                    )
                }
            }

            if (showAddSheet) {
                AddEditPasswordBottomSheet(
                    onDismiss = { showAddSheet = false },
                    existingPassword = selectedPassword,
                    onSaved = {
                        showAddSheet = false
                    }
                )
            }
        }
    }
}