package com.patrickmota.leitor

import android.app.Application
import com.patrickmota.leitor.di.KoinModules.databaseModule
import com.patrickmota.leitor.di.KoinModules.repositoryModule
import com.patrickmota.leitor.di.KoinModules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LeitorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LeitorApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }
}