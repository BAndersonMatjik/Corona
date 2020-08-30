package com.beone.bestpractice.core.data.source.remote

import android.util.Log
import com.beone.bestpractice.core.data.source.remote.network.ApiResponse
import com.beone.bestpractice.core.data.source.remote.network.ApiService
import com.beone.bestpractice.core.data.source.remote.response.CountryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource constructor(private val apiService: ApiService){
    companion object{
        private const val TAG = "RemoteDataSource"

    }
    fun getAllCountry(): Flow<ApiResponse<List<CountryResponse>>> {

        return flow {
            try {
               apiService.getCountries().apply {
                   if (this.isNotEmpty()){
                       emit(ApiResponse.Success(this))
                   }else{
                       emit(ApiResponse.Empty)
                   }
               }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getAllCountry: ",e)
            }
        }.flowOn(Dispatchers.IO)
    }
}