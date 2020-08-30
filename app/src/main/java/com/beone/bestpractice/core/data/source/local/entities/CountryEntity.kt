package com.beone.bestpractice.core.data.local.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.jetbrains.annotations.Nullable

@Parcelize
@Entity(tableName = "country")
data class CountryEntity(
        @PrimaryKey(autoGenerate = true)
        @Nullable
        val countryId: Int?,
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
        @Embedded val countryInfoEntity: CountryInfoEntity
):Parcelable

@Parcelize
data class CountryInfoEntity(
        var _id: Int = 0,
        var flag: String ="",
         var iso2: String ="",
         var iso3: String ="",
        var lat: Double =0.0,
        var long: Double=0.0
):Parcelable