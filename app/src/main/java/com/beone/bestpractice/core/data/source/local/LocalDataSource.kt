package com.beone.bestpractice.core.data.local

import androidx.lifecycle.LiveData
import com.beone.bestpractice.core.data.local.entities.CountryEntity
import com.beone.bestpractice.core.data.local.room.CountryDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val countryDao: CountryDao){
    fun getCountry(): Flow<List<CountryEntity>> = countryDao.getAllCountry()
    suspend fun insertCountry(countryList: List<CountryEntity>) =  countryDao.insertCountry(countryList)
}