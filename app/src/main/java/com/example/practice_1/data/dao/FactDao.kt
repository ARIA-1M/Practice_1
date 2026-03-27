package com.example.practice_1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practice_1.data.entity.Fact
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Query("SELECT * FROM facts ORDER BY id ASC")
    fun getAllFact(): Flow<List<Fact>>

    @Insert
    suspend fun insert(fact: Fact)

    @Insert
    suspend fun insertAll(facts: List<Fact>)

    @Query("SELECT COUNT(*) FROM facts")
    suspend fun getCount(): Int
}