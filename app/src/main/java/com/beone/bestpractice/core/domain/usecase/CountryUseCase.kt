package com.beone.bestpractice.core.domain.usecase

import androidx.lifecycle.LiveData
import com.beone.bestpractice.core.data.Resource
import com.beone.bestpractice.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryUseCase {
    fun getAllCountry(): Flow<Resource<List<Country>>>
}