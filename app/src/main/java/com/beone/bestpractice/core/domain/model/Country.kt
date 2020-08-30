package com.beone.bestpractice.core.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.Nullable

@Parcelize
data class Country(
        @Nullable
        val countryId: Int,
        val active: Int,
        val activePerOneMillion: Double,
        val cases: Int,
        val casesPerOneMillion: Int,
        val continent: String,
        val country: String,
        val critical: Int,
        val criticalPerOneMillion: Double,
        val deaths: Int,
        val deathsPerOneMillion: Double,
        val oneCasePerPeople: Int,
        val oneDeathPerPeople: Int,
        val oneTestPerPeople: Int,
        val population: Int,
        val recovered: Int,
        val recoveredPerOneMillion: Double,
        val tests: Int,
        val testsPerOneMillion: Int,
        val todayCases: Int,
        val todayDeaths: Int,
        val todayRecovered: Int,
        val updated: Long,
        val countryInfo: CountryInfo
) : Parcelable

@Parcelize
data class CountryInfo(
        var _id: Int =0,
        var flag: String,
         var iso2: String?,
         var iso3: String?,
        var lat: Double,
        var long: Double
) : Parcelable