package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import di.Providers
import ui.uimodel.GeminiUiEvent
import ui.screen.GeminiScreen
import ui.screen.GeminiDetailScreen

@Composable
fun GeminiApp(
    viewModel: GeminiViewModel = Providers.viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val state by viewModel.state.collectAsState()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GeminiRoute.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = GeminiRoute.Main.name) {
                GeminiScreen(
                    uiModel = state,
                    onSummarizeBook = { command, byteArrayImage ->
                        viewModel.sendAction(GeminiUiEvent.RequestWithAttachment(command, byteArrayImage))
                    },
                    navigateToDetailPageClicked = { result ->
                        navController.navigate("detail")
                    }
                )
            }

            composable(
                route = GeminiRoute.Detail.name,
//                arguments = listOf(
//                    navArgument(GeminiRoute.Arguments.CONTENT_ID) { type = NavType.StringType }
//                )
            ) { backStackEntry ->
//                val content = backStackEntry.arguments
//                    ?.getString(GeminiRoute.Arguments.CONTENT_ID)
//                    .orEmpty()

                GeminiDetailScreen(
                    uiModel = state,
                    onBack = {
                        resetStateAndNavigateToStart(viewModel, navController)
                    }
                )
            }
        }
    }
}

private fun resetStateAndNavigateToStart(
    viewModel: GeminiViewModel,
    navController: NavHostController
) {
    viewModel.resetState()
    navController.popBackStack(GeminiRoute.Main.name, inclusive = false)
}