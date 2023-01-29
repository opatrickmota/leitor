package com.patrickmota.leitor.repository

import com.patrickmota.leitor.model.Leitor
import kotlinx.coroutines.flow.Flow

interface LeitorRepository {
    suspend fun addUrl(leitor: Leitor)
    fun getUrls(): Flow<List<Leitor>>
    suspend fun deleteUrl(leitor: Leitor)
}