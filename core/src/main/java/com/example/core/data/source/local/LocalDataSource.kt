package com.example.core.data.source.local

import com.example.core.data.source.local.entities.CountryEntity
import com.example.core.data.source.local.room.CountryDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val countryDao: CountryDao){
    fun getCountry(): Flow<List<CountryEntity>> = countryDao.getAllCountry()
    suspend fun insertCountry(countryList: List<CountryEntity>) =  countryDao.insertCountry(countryList)
    fun getFavoriteCountry(): Flow<List<CountryEntity>> = countryDao.getAllFavoriteCountry()
    fun updateFavoriteCountry(country:String,status:Boolean) = countryDao.updateFavoriteCountry(country,status)
}