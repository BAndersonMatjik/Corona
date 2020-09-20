package com.example.core.di

import androidx.room.Room
import com.example.core.data.CountryRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.CountryDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.ICountryRepository
import com.example.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BaseUrl = "https://corona.lmao.ninja/v2/"
val databaseModule = module {
    factory { get<CountryDatabase>().countryDao() }
    single {
        val passphrase= SQLiteDatabase.getBytes("corona2020".toCharArray())
        val encryptionFactory = SupportFactory(passphrase)
        Room.databaseBuilder(
                androidContext(),
                CountryDatabase::class.java, "country.db"
        ).fallbackToDestructiveMigration()
                .openHelperFactory(encryptionFactory).build()
    }
}
val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
                .add("corona.lmao.ninja", "sha256/0hKtQOZLdONM5CAGfrO/uHRHI3UrH6zLHvST+VW/SIo=")
                .build()

        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
    }
    single {
        Retrofit.Builder()
                .baseUrl(BaseUrl)
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
        CountryRepository(get(), get(), get())
    }
}