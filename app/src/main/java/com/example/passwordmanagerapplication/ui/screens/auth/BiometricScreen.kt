package com.example.passwordmanagerapplication.ui.screens.auth



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.example.passwordmanagerapplication.data.security.BiometricAuthenticator


@Composable
fun BiometricScreen(
    title: String = "Authenticate",
    subtitle: String = "Use biometrics or device credentials",
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    LaunchedEffect(Unit) {
        if (activity == null) {
            onError("Biometric requires an Activity context")
            return@LaunchedEffect
        }

        if (BiometricAuthenticator.canAuthenticate(context)) {
            BiometricAuthenticator.prompt(
                activity = activity,
                title = title,
                subtitle = subtitle,
                onSuccess = { onSuccess() },
                onError = { onError(it) }
            )
        } else {
            onError("Biometric authentication not available")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Authenticate to continue...")
    }
}
