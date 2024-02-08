package com.atom.shop.di

import android.app.Application
import androidx.room.Room
import com.atom.shop.data.dao.ProductDao
import com.atom.shop.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}

