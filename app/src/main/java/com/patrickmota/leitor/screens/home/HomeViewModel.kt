package com.patrickmota.leitor.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrickmota.leitor.model.Leitor
import com.patrickmota.leitor.repository.LeitorRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val defaultDispatcher: CoroutineDispatcher,
    private val leitorRepository: LeitorRepository
) : ViewModel() {

    fun addUrl(leitor: Leitor) = viewModelScope.launch(defaultDispatcher) {
        leitorRepository.addUrl(leitor)
    }

    fun getUrls(): Flow<List<Leitor>> {
        return flow {
            leitorRepository.getUrls().collect {
                emit(it)
            }
        }
    }

    fun deleteUrl(leitor: Leitor) = viewModelScope.launch(defaultDispatcher) {
        leitorRepository.deleteUrl(leitor)
    }
}