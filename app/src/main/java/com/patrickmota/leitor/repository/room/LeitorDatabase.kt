package com.patrickmota.leitor.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patrickmota.leitor.model.Leitor

@Database(entities = [Leitor::class], version = 1)
abstract class LeitorDatabase : RoomDatabase() {
    abstract fun leitorDao(): LeitorDao
}