package com.example.practice_1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.practice_1.data.entity.Fact
import com.example.practice_1.repository.FactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope

class FactViewModel(private val repository: FactRepository) : ViewModel(){


    val allFact: StateFlow<List<Fact>> = repository.allFact
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}