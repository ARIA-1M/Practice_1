package com.example.practice_1.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val breed: String,
    val years: Int,
    val imageRes: Int,
    val description: String,
    val userId: Int
)