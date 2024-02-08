package com.atom.shop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atom.shop.data.converter.FeedbackConverter
import com.atom.shop.data.converter.InfoListConverter
import com.atom.shop.data.converter.PriceConverter
import com.atom.shop.data.converter.StringListConverter
import com.atom.shop.data.dao.ProductDao
import com.atom.shop.domain.dto.ProductEntity


@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    InfoListConverter::class,
    FeedbackConverter::class,
    PriceConverter::class,
    StringListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}