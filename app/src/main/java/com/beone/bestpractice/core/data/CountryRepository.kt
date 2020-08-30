package com.beone.bestpractice.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.beone.bestpractice.core.data.local.LocalDataSource
import com.beone.bestpractice.core.data.source.remote.RemoteDataSource
import com.beone.bestpractice.core.data.source.remote.network.ApiResponse
import com.beone.bestpractice.core.data.source.remote.response.CountryResponse
import com.beone.bestpractice.core.domain.model.Country
import com.beone.bestpractice.core.domain.repository.ICountryRepository
import com.beone.bestpractice.core.utils.AppExecutors
import com.beone.bestpractice.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
):ICountryRepository{
    override fun getAllCountry(): Flow<Resource<List<Country>>> {
       return object : NetworkBoundResource<List<Country>,List<CountryResponse>>(){
           override fun loadFromDB(): Flow<List<Country>> {
             return localDataSource.getCountry().map {
                 DataMapper.mapEntitiesToDomain(it)
             }
           }

           override fun shouldFetch(data: List<Country>?): Boolean {
                return true
           }

           override suspend fun createCall(): Flow<ApiResponse<List<CountryResponse>>> {
            return remoteDataSource.getAllCountry()
           }

           override suspend fun saveCallResult(data: List<CountryResponse>) {
                DataMapper.mapResponsesToEntities(data).apply {
                    localDataSource.insertCountry(this)
                }
           }

       }.asFlow()
    }

}