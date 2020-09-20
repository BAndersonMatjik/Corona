package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.CountryResponse
import retrofit2.http.GET

interface ApiService {
    @GET("countries")
    suspend fun getCountries(): List<CountryResponse>
}