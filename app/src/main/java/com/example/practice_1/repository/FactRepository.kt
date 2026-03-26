package com.example.practice_1.repository

import com.example.practice_1.data.dao.FactDao
import com.example.practice_1.data.entity.Fact
import kotlinx.coroutines.flow.Flow

class FactRepository (private val factDao: FactDao){
    val allFact: Flow<List<Fact>> = factDao.getAllFact()
}