package com.example.practice_1.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class Fact (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageRes: Int,
    val description: String
)