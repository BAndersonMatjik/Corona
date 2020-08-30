package com.beone.bestpractice

import android.app.Application
import android.os.StrictMode
import com.beone.bestpractice.core.di.databaseModule
import com.beone.bestpractice.core.di.networkModule
import com.beone.bestpractice.core.di.repositoryModule
import com.beone.bestpractice.di.useCaseModule
import com.beone.bestpractice.di.viewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@BaseApp)
            modules(listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelFactory
            )
            )
        }

    }
}