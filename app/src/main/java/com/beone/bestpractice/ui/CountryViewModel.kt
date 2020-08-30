package com.beone.bestpractice.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beone.bestpractice.core.domain.usecase.CountryInteractor
import com.beone.bestpractice.core.domain.usecase.CountryUseCase


class CountryViewModel(useCase: CountryInteractor) : ViewModel() {

    val country = useCase.getAllCountry().asLiveData()
}