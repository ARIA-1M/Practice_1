package com.example.practice_1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practice_1.data.dao.FactDao
import com.example.practice_1.data.entity.Fact

@Database(
    entities = [Fact::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase  : RoomDatabase(){
    abstract fun factDao() : FactDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cat_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}