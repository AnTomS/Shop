package com.atom.shop.domain.dto


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.atom.shop.data.converter.FeedbackConverter
import com.atom.shop.data.converter.InfoListConverter
import com.atom.shop.data.converter.PriceConverter
import com.atom.shop.data.converter.StringListConverter

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,

    @TypeConverters(PriceConverter::class)
    val price: PriceEntity,

    @TypeConverters(FeedbackConverter::class)
    val feedback: FeedbackEntity,

    @TypeConverters(StringListConverter::class)
    val tags: List<String>,

    val available: Int,
    val description: String,

    @TypeConverters(InfoListConverter::class)
    val info: List<InfoEntity>,
    val ingredients: String,
    val favourite: Boolean = false
)