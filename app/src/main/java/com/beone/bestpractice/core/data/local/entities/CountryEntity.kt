package com.beone.bestpractice.core.data.local

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "country")
data class CountryEntity(
        val active: Int,
        val activePerOneMillion: Double,
        val cases: Int,
        val casesPerOneMillion: Int,
        val continent: String,
        val country: String,
        val countryInfo: CountryInfoEntity,
        val critical: Int,
        val criticalPerOneMillion: Double,
        val deaths: Int,
        val deathsPerOneMillion: Int,
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
        val updated: Long
):Parcelable

@Parcelize
data class CountryInfoEntity(
        val _id: Int,
        val flag: String,
        val iso2: String,
        val iso3: String,
        val lat: Int,
        val long: Int
):Parcelable