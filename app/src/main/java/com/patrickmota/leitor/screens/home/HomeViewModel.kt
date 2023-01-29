package com.patrickmota.leitor.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrickmota.leitor.model.Leitor
import com.patrickmota.leitor.repository.LeitorRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val defaultDispatcher: CoroutineDispatcher,
    private val leitorRepository: LeitorRepository
) : ViewModel() {

    var leitor: List<Leitor> = emptyList()

    init {
        viewModelScope.launch {
            leitorRepository.getUrls().collect {
                leitor = it
            }
        }
    }

    fun addUrl(leitor: Leitor) = viewModelScope.launch(defaultDispatcher) {
        leitorRepository.addUrl(leitor)
    }

    fun getUrls(): List<Leitor> {
        viewModelScope.launch(defaultDispatcher) {
            leitorRepository.getUrls().collect {
                leitor = it
            }
        }
        return leitor
    }

    fun deleteUrl(leitor: Leitor) = viewModelScope.launch(defaultDispatcher) {
        leitorRepository.deleteUrl(leitor)
    }
}