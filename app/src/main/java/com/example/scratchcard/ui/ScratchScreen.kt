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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.scratchcard.R
import com.example.scratchcard.viewmodel.ScratchViewModel


@Composable
fun ScratchScreen(
    viewModel: ScratchViewModel
) {
    val scratchCard = viewModel.scratchCard
    val isScratching = viewModel.isScratching
    val canBeScratched = viewModel.canBeScratched


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
                    enabled = !isScratching && canBeScratched
                ) {
                    Text(
                        text = if (isScratching) {
                            stringResource(R.string.scratching)
                        } else if (canBeScratched) {
                            stringResource(R.string.scratch_the_card)
                        } else {
                            stringResource(R.string.cannot_scratch)
                        }
                    )
                }

                Text(
                    text = stringResource(R.string.scratched_code, scratchCard.code),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    )
}