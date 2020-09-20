package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getAllCountry() : Flow<Resource<List<Country>>>
    fun getAllFavoriteCountry():Flow<List<Country>>
    fun updateFavoriteCountry(country:String,status:Boolean)
}