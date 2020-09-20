package com.example.favoritefeature

import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.CountryUseCase


class DetailViewModel(private val useCase: CountryUseCase) : ViewModel() {
    fun updateFavoriteCountry(country: String,status: Boolean) {
        useCase.updateFavoriteCountry(country,status)
    }
}