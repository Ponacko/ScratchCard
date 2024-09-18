package com.example.scratchcard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratchcard.ui.ActivationScreen
import com.example.scratchcard.ui.MainScreen
import com.example.scratchcard.ui.ScratchScreen
import com.example.scratchcard.viewmodel.ActivationViewModel
import com.example.scratchcard.viewmodel.ScratchViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ScratchCardApp(
    viewModel: ScratchViewModel = koinViewModel(),
    activationViewModel: ActivationViewModel = koinViewModel()
) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavigationDestinations.MAIN.route) {
        composable("main") {
            MainScreen(
                scratchCardState = viewModel.scratchCard.state,
                onScratchClick = { navController.navigate(NavigationDestinations.SCRATCH.route) },
                onActivateClick = { navController.navigate(NavigationDestinations.ACTIVATE.route) }
            )
        }

        composable(NavigationDestinations.SCRATCH.route) {
            ScratchScreen(
                viewModel = viewModel,
            )
        }

        composable(NavigationDestinations.ACTIVATE.route) {
            ActivationScreen(
                viewModel = activationViewModel,
            )
        }
    }
}