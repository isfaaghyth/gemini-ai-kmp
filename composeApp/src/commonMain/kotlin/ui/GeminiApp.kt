package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import di.Di

@Composable
fun GeminiApp(
    viewModel: GeminiViewModel = viewModel {
        GeminiViewModel(
            Di.provideUseCase()
        )
    },
    navController: NavHostController = rememberNavController()
) {
    val backStack by navController.currentBackStackEntryAsState()

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
                    navigateToDetailPageClicked = {
                        navController.navigate("detail/what-is-kotlin")
                    }
                )
            }

            composable(
                route = GeminiRoute.Detail.name,
                arguments = listOf(
                    navArgument(GeminiRoute.Arguments.CONTENT_ID) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val content = backStackEntry.arguments?.getString(GeminiRoute.Arguments.CONTENT_ID).orEmpty()
                
                GeminiDetailScreen(
                    viewModel = viewModel,
                    content = content,
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

sealed class GeminiRoute(val name: String) {
    data object Main : GeminiRoute("main")
    data object Detail : GeminiRoute("detail/{${Arguments.CONTENT_ID}}")

    object Arguments {
        const val CONTENT_ID = "contentId"
    }
}