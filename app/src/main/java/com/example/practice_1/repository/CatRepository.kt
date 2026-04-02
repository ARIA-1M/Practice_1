package com.example.practice_1.repository

import androidx.annotation.WorkerThread
import com.example.practice_1.data.dao.CatDao
import com.example.practice_1.data.entity.Cat
import com.example.practice_1.data.entity.User
import kotlinx.coroutines.flow.Flow

class CatRepository (private val catDao: CatDao) {

    val allCat: Flow<List<Cat>> = catDao.getAllCat()

    @WorkerThread
    suspend fun insert(item: Cat) {
        catDao.insert(item)
    }

    @WorkerThread
    suspend fun update(item: Cat) {
        catDao.update(item)
    }

    @WorkerThread
    suspend fun delete(item: Cat) {
        catDao.delete(item)
    }

    suspend fun getItemById(id: Int): Cat? {
        return catDao.getCatById(id)
    }
}