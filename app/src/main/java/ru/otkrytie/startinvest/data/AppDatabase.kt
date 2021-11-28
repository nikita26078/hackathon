package ru.otkrytie.startinvest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.otkrytie.startinvest.data.models.Comment
import ru.otkrytie.startinvest.data.models.Investment
import ru.otkrytie.startinvest.data.models.News
import ru.otkrytie.startinvest.data.models.Subscription

@Database(
    entities = [Comment::class, Investment::class, News::class, Subscription::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "invest_database.db"
                )
                    .createFromAsset("invest_database.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
