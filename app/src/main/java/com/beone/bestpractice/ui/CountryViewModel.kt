package com.beone.bestpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.CountryInteractor


class CountryViewModel(useCase: CountryInteractor) : ViewModel() {

    val country = useCase.getAllCountry().asLiveData()
}