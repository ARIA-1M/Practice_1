package com.example.practice_1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_1.data.entity.User
import com.example.practice_1.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.text.insert

class UserViewModel (private val repository: UserRepository) : ViewModel() {

    val allUser: StateFlow<List<User>> = repository.getAllUser
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var selectedUser by mutableStateOf<User?>(null)
        private set


    fun loginOrRegister(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            // Получаем список всех пользователей
            val allUsers = repository.getAllUser.first()
            val existingUser = allUsers.find { it.email == email && it.password == password }

            if (existingUser != null) {
                selectedUser = existingUser
                onSuccess()
            } else {
                val newUser = User(
                    email = email,
                    password = password
                )
                repository.insert(newUser)

                // Получаем обновлённый список
                val updatedUsers = repository.getAllUser.first()
                val savedUser = updatedUsers.find { it.email == email }
                selectedUser = savedUser
                onSuccess()
            }
        }
    }

}