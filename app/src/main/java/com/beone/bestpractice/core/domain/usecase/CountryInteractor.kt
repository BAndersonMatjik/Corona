package com.beone.bestpractice.core.domain.usecase

import androidx.lifecycle.LiveData
import com.beone.bestpractice.core.data.Resource
import com.beone.bestpractice.core.domain.model.Country
import com.beone.bestpractice.core.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow

class CountryInteractor(private val countryRepository: ICountryRepository):CountryUseCase {
    override fun getAllCountry(): Flow<Resource<List<Country>>> {
       return countryRepository.getAllCountry()
    }
}