package com.beone.bestpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.CountryUseCase

class FavoriteViewModel(countryUseCase: CountryUseCase) : ViewModel() {
    val favoriteCountry = countryUseCase.getAllFavoriteCountry().asLiveData()
}