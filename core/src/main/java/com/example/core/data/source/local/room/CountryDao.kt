package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("Select * From country")
    fun getAllCountry(): Flow<List<CountryEntity>>

    @Query("Select * From country where isFavorite = 1")
    fun getAllFavoriteCountry(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countrys: List<CountryEntity>)

    @Query("UPDATE country set isFavorite = :status where country = :country ")
    fun updateFavoriteCountry(country:String,status:Boolean)
}