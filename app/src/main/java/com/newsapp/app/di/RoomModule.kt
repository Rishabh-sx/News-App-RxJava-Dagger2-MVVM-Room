package com.newsapp.app.di

import android.app.Application
import androidx.room.Room
import com.newsapp.app.data.database.AppDatabase
import com.newsapp.app.data.database.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDatabase): NewsDao {
        return demoDatabase.newsDao()
    }
}