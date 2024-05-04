package di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import domain.usecase.GetContentUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.GeminiViewModel

internal class Providers : KoinComponent {

    private val useCase: GetContentUseCase by inject()

    @Composable
    fun viewModel() = viewModel { GeminiViewModel(useCase) }
}