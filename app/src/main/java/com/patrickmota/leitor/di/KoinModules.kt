package com.patrickmota.leitor.di

import android.app.Application
import androidx.room.Room
import com.patrickmota.leitor.repository.LeitorRepository
import com.patrickmota.leitor.repository.LeitorRepositoryImpl
import com.patrickmota.leitor.repository.room.LeitorDao
import com.patrickmota.leitor.repository.room.LeitorDatabase
import com.patrickmota.leitor.screens.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {
    val databaseModule = module {
        fun provideDatabase(application: Application): LeitorDatabase {
            return Room.databaseBuilder(application, LeitorDatabase::class.java, "LEITORDB")
                .build()
        }

        fun provideDao(database: LeitorDatabase): LeitorDao {
            return database.leitorDao()
        }

        single { provideDatabase(androidApplication()) }
        single { provideDao(get()) }
    }

    val repositoryModule = module {
        single<LeitorRepository> { LeitorRepositoryImpl(get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(defaultDispatcher = Dispatchers.IO, get()) }
    }
}