package com.example.practice_1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_1.data.entity.Cat
import com.example.practice_1.data.entity.User
import com.example.practice_1.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.String
import kotlin.text.insert

class CatViewModel(private val repository: CatRepository): ViewModel() {
    val allCat: StateFlow<List<Cat>> = repository.allCat
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )



    var selectedCat by mutableStateOf<Cat?>(null)
        private set

    fun selectCat(item: Cat) {
        selectedCat = item
    }

    fun clearSelectedCat() {
        selectedCat = null
    }
    fun insertCat( name: String, breed: String, years: Int, imageRes: Int, description: String, userId: Int) {
        viewModelScope.launch {
            val cat = Cat(
                name = name,
                breed = breed,
                years = years,
                imageRes = imageRes,
                description = description,
                userId = userId
            )
            repository.insert(cat)
        }
    }




    fun update(id: Int, name: String, breed: String, years: Int, imageRes: Int, description: String, userId: Int) {
        viewModelScope.launch {
            val item = Cat(
                id = id,
                name = name,
                breed = breed,
                years = years,
                imageRes = imageRes,
                description = description,
                userId = userId
            )
            repository.update(item)

        }
    }

    fun delete(cat: Cat) {
        viewModelScope.launch {
            repository.delete(cat)
        }
    }
}