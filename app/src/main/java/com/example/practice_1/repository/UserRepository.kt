package com.example.practice_1.repository

import androidx.annotation.WorkerThread
import com.example.practice_1.data.dao.FactDao
import com.example.practice_1.data.dao.UserDao
import com.example.practice_1.data.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository (private val userDao: UserDao){

    val getAllUser: Flow<List<User>> = userDao.getAllUser()

    @WorkerThread
    suspend fun insert(item: User) {
        userDao.insert(item)
    }

    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }
}