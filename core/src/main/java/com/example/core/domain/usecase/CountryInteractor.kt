package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Country
import com.example.core.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow

class CountryInteractor(private val countryRepository: ICountryRepository):CountryUseCase {
    override fun getAllCountry(): Flow<Resource<List<Country>>> {
       return countryRepository.getAllCountry()
    }

    override fun getAllFavoriteCountry(): Flow<List<Country>> {
        return countryRepository.getAllFavoriteCountry()
    }

    override fun updateFavoriteCountry(country: String,status:Boolean) {
        countryRepository.updateFavoriteCountry(country,status)
    }
}