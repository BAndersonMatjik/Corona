package com.beone.bestpractice.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beone.bestpractice.core.data.local.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}