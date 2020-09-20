package com.example.favoritefeature.di

import com.example.core.domain.usecase.CountryInteractor
import com.example.core.domain.usecase.CountryUseCase
import com.example.favoritefeature.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel {
        DetailViewModel(get<CountryUseCase>() as CountryInteractor)
    }
}