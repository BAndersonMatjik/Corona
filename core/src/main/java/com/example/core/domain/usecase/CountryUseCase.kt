package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryUseCase {
    fun getAllCountry(): Flow<Resource<List<Country>>>
    fun getAllFavoriteCountry(): Flow<List<Country>>
    fun updateFavoriteCountry(country:String,status: Boolean)
}