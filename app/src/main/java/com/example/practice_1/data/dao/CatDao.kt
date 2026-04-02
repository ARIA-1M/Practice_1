package com.example.practice_1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practice_1.data.entity.Cat
import com.example.practice_1.data.entity.Fact
import com.example.practice_1.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert
    suspend fun insert(item: Cat)

    @Update
    suspend fun update(item: Cat)

    @Delete
    suspend fun delete(item: Cat)

    @Query("SELECT * FROM cats ORDER BY id DESC")
    fun getAllCat(): Flow<List<Cat>>

    @Query("SELECT * FROM cats WHERE id = :id")
    suspend fun getCatById(id: Int): Cat?
}

