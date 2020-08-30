package com.beone.bestpractice.di

import com.beone.bestpractice.core.domain.usecase.CountryInteractor
import com.beone.bestpractice.core.domain.usecase.CountryUseCase
import com.beone.bestpractice.ui.CountryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CountryUseCase> { CountryInteractor(get()) }
}
val viewModelFactory= module {
    viewModel {
        CountryViewModel(get<CountryUseCase>() as CountryInteractor)
    }
}