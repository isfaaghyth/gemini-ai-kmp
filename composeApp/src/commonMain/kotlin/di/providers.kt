package di

import data.repository.GeminiRepositoryImpl
import domain.interactor.GeminiRepository
import domain.usecase.GetContentUseCase

object Di {
    
    private fun provideRepository(): GeminiRepository {
        return GeminiRepositoryImpl(GeminiClient.app.create())
    }
    
    fun provideUseCase(): GetContentUseCase {
        return GetContentUseCase(provideRepository())
    }
}