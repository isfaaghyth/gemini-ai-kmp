package di

import data.repository.GeminiRepositoryImpl
import di.network.GeminiApi
import domain.interactor.GeminiRepository
import domain.usecase.GetContentUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule() = listOf(
    domainModule,
    dataModule
)

val domainModule = module {
    singleOf(::GetContentUseCase)
}

val dataModule = module {
    single<GeminiApi> { GeminiClient.app.create() }
    single<GeminiRepository> { GeminiRepositoryImpl(get()) }
}