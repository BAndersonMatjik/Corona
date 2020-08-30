package com.beone.bestpractice.core.domain.repository

import androidx.lifecycle.LiveData
import com.beone.bestpractice.core.data.Resource
import com.beone.bestpractice.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getAllCountry() : Flow<Resource<List<Country>>>
}