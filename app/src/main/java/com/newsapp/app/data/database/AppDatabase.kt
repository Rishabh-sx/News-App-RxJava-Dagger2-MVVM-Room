package com.newsapp.app.data.database

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsapp.app.dataclass.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    open abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_NAME = "news"
    }
}