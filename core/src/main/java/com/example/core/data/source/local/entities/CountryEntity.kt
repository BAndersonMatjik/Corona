package com.example.core.data.source.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
        val active: Int,
        val activePerOneMillion: Double,
        val cases: Int,
        val casesPerOneMillion: Int,
        val continent: String,
        @PrimaryKey
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
        @Embedded val countryInfoEntity: CountryInfoEntity,
        var isFavorite:Boolean = false
)

data class CountryInfoEntity(
        var _id: Int = 0,
        var flag: String =" ",
         var iso2: String =" ",
         var iso3: String =" ",
        var lat: Double =0.0,
        var long: Double=0.0
)