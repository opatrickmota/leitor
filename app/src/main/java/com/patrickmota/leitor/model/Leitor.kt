package com.patrickmota.leitor.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leitor")
data class Leitor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String
)