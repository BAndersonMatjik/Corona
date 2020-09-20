package com.beone.bestpractice.di

import com.beone.bestpractice.ui.CountryViewModel
import com.beone.bestpractice.ui.FavoriteViewModel
import com.example.core.domain.usecase.CountryInteractor
import com.example.core.domain.usecase.CountryUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CountryUseCase> { CountryInteractor(get()) }
}
val viewModelFactory= module {
    viewModel {
        CountryViewModel(get<CountryUseCase>() as CountryInteractor)
    }
    viewModel {
        FavoriteViewModel(get<CountryUseCase>() as CountryInteractor)
    }

}