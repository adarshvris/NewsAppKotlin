package com.adarsh.newsappkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adarsh.newsappkotlin.api.GoogleNewsDetail
import com.adarsh.newsappkotlin.api.IndianNewsDetail
import com.adarsh.newsappkotlin.constants.DATABASE_NAME
import com.adarsh.newsappkotlin.db.dao.NewsDao

@Database(
    entities = [GoogleNewsDetail::class,
        IndianNewsDetail::class], version = 6, exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        private var newsDatabaseInstance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = newsDatabaseInstance ?: synchronized(LOCK) {
            newsDatabaseInstance ?: createDatabase(context).also { newsDatabaseInstance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}