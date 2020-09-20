package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.CountryResponse
import com.example.core.domain.model.Country
import com.example.core.domain.repository.ICountryRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : ICountryRepository {
    override fun getAllCountry(): Flow<Resource<List<Country>>> {
        return object : NetworkBoundResource<List<Country>, List<CountryResponse>>() {
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

    override fun getAllFavoriteCountry(): Flow<List<Country>> {
        return localDataSource.getFavoriteCountry().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun updateFavoriteCountry(country: String, status: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteCountry(country, status)
        }
    }

}