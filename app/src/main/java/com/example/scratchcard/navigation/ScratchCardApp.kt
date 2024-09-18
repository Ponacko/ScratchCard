package com.example.scratchcard.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratchcard.model.ScratchCardState
import com.example.scratchcard.ui.ActivationScreen
import com.example.scratchcard.ui.MainScreen
import com.example.scratchcard.ui.ScratchViewModel
import com.example.scratchcard.ui.ScratchScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun ScratchCardApp(viewModel: ScratchViewModel = koinViewModel()) {

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
                onScratchCompleted = {
                    // TODO scratch card
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationDestinations.ACTIVATE.route) {
            ActivationScreen(
                onActivateCompleted = {
                    // TODO activate card
                    navController.popBackStack()
                }
            )
        }
    }
}