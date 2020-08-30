package com.beone.bestpractice.core.di

import androidx.room.Room
import com.beone.bestpractice.core.data.CountryRepository
import com.beone.bestpractice.core.data.local.LocalDataSource
import com.beone.bestpractice.core.data.local.room.CountryDatabase
import com.beone.bestpractice.core.data.source.remote.RemoteDataSource
import com.beone.bestpractice.core.data.source.remote.network.ApiService
import com.beone.bestpractice.core.domain.repository.ICountryRepository
import com.beone.bestpractice.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module{
    factory { get<CountryDatabase>().countryDao() }
    single {
        Room.databaseBuilder(
                androidContext(),
                CountryDatabase::class.java,"country.db"
        ).fallbackToDestructiveMigration().build()
    }
}
val networkModule = module {
    single{
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }
    single {
          Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build().create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICountryRepository> {
        CountryRepository(get(),get(),get())
    }
}