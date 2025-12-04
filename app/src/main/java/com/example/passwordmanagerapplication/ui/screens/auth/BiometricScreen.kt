package com.example.passwordmanagerapplication.ui.screens.auth



import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun BiometricScreen(
    onSuccess: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val activity = context as FragmentActivity

    LaunchedEffect(Unit) {
        viewModel.authenticate(activity)
    }

    LaunchedEffect(Unit) {
        viewModel.authEvent.collect { isAuthenticated ->
            if (isAuthenticated) {
                onSuccess()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

    }
}