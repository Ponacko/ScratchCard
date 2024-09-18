package com.example.scratchcard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ScratchScreen(
    viewModel: ScratchViewModel
) {
    val scratchCard = viewModel.scratchCard
    val isScratching = viewModel.isScratching


    DisposableEffect(Unit) {
        onDispose {
            viewModel.cancelScratching()
        }
    }
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (!isScratching) {
                            viewModel.scratch()
                        }
                    },
                    enabled = !isScratching
                ) {
                    Text(text = if (isScratching) "Scratching..." else "Scratch the Card")
                }

                Text(text = "Scratched Code: ${scratchCard.code}", modifier = Modifier.padding(top = 16.dp))
            }
        }
    )
}