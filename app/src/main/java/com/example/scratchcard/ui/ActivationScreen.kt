package com.example.scratchcard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.scratchcard.viewmodel.ActivationViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ActivationScreen(
    viewModel: ActivationViewModel = koinViewModel(),
) {
    val isActivating = viewModel.isActivating
    val activationError = viewModel.activationError

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (!isActivating) {
                    viewModel.activate()
                }
            },
            enabled = !isActivating
        ) {
            Text(text = if (isActivating) "Activating..." else "Activate the Card")
        }

        activationError?.let {
            AlertDialog(
                onDismissRequest = { viewModel.clearError() },
                title = { Text("Activation Error") },
                text = { Text(it) },
                confirmButton = {
                    Button(onClick = { viewModel.clearError() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
