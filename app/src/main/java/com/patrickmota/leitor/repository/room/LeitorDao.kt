package com.patrickmota.leitor.repository.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.patrickmota.leitor.model.Leitor
import kotlinx.coroutines.flow.Flow

@Dao
interface LeitorDao {
    @Insert
    suspend fun addUrl(leitor: Leitor)

    @Transaction
    @Query("SELECT * FROM leitor ORDER BY id DESC")
    fun getUrls(): Flow<List<Leitor>>

    @Delete
    suspend fun deleteUrl(leitor: Leitor)
}