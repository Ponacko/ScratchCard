package com.example.scratchcard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scratchcard.model.ScratchCardState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScratchCardApp()
        }
    }
}

@Composable
fun ScratchCardApp(viewModel: MainViewModel = viewModel()) {
    // Main Screen UI
    MainScreen(
        scratchCardState = viewModel.scratchCard.state,
        onScratchClick = { /* Navigate to Scratch Screen */ },
        onActivateClick = { /* Navigate to Activation Screen */ }
    )
}

@Composable
fun MainScreen(
    scratchCardState: ScratchCardState,
    onScratchClick: () -> Unit,
    onActivateClick: () -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Card State: ${scratchCardState.name}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onScratchClick) {
                    Text(text = "Go to Scratch Screen")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = onActivateClick) {
                    Text(text = "Go to Activation Screen")
                }
            }
        }
    )
}