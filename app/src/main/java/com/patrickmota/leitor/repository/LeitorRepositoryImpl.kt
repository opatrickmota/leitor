package com.patrickmota.leitor.repository

import com.patrickmota.leitor.model.Leitor
import com.patrickmota.leitor.repository.room.LeitorDao
import kotlinx.coroutines.flow.Flow

class LeitorRepositoryImpl(private val leitorDao: LeitorDao) : LeitorRepository {
    override suspend fun addUrl(leitor: Leitor) = leitorDao.addUrl(leitor)

    override fun getUrls(): Flow<List<Leitor>> = leitorDao.getUrls()

    override suspend fun deleteUrl(leitor: Leitor) = leitorDao.deleteUrl(leitor)
}