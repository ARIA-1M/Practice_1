package com.example.practice_1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practice_1.data.dao.CatDao
import com.example.practice_1.data.dao.FactDao
import com.example.practice_1.data.dao.UserDao
import com.example.practice_1.data.entity.Cat
import com.example.practice_1.data.entity.Fact
import com.example.practice_1.data.entity.User

@Database(
    entities = [Fact::class, User::class, Cat::class],
    version = 4,
    exportSchema = false
)

abstract class AppDatabase  : RoomDatabase(){
    abstract fun factDao() : FactDao
    abstract fun userDao(): UserDao

    abstract fun catDao(): CatDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cat_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}
