package com.beone.bestpractice.core.data.source.remote.network

import com.beone.bestpractice.core.data.source.remote.response.CountryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("countries")
    suspend fun getCountries(): List<CountryResponse>
}