package com.example.scratchcard.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.scratchcard.R
import com.example.scratchcard.model.ScratchCardState

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
                    Text(text = stringResource(R.string.go_to_scratch_screen))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = onActivateClick) {
                    Text(text = stringResource(R.string.go_to_activation_screen))
                }
            }
        }
    )
}