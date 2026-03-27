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
/*package com.example.practice_1.data.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practice_1.R
import com.example.practice_1.data.dao.FactDao
import com.example.practice_1.data.entity.Fact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Fact::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cat_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // 👇 ПЕРЕДАЁМ db, А НЕ instance
                        prepopulate(instance )
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private fun prepopulate(db: AppDatabase) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val count = db.factDao().getCount()
                    if (count == 0) {
                        val facts = listOf(
                            Fact(1, R.drawable.cat_meow, "Кошки не мяукают друг с другом — это только для людей"),
                            Fact(2, R.drawable.cat_nose, "Рисунок на носу кошки уникален, как отпечаток пальца"),
                            Fact(3, R.drawable.cat_m, "На лбу у кошек есть рисунок в виде буквы М"),
                            Fact(4, R.drawable.cat_whiskers, "Усы кошки показывают её настроение"),
                            Fact(5, R.drawable.cat_kitten, "Кошка может иметь более 100 котят за жизнь"),
                            Fact(6, R.drawable.cat_fall, "Кошки всегда приземляются на лапы"),
                            Fact(7, R.drawable.cat_sleep, "Кошки спят около 16 часов в день"),
                            Fact(8, R.drawable.cat_paws, "У кошек потеют только подушечки лап"),
                            Fact(9, R.drawable.cat_sound, "Кошки издают около 100 звуков (собаки только 10)"),
                            Fact(10, R.drawable.cat_jump, "Кошка прыгает в 5 раз выше своего роста")
                        )
                        db.factDao().insertAll(facts)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}*/