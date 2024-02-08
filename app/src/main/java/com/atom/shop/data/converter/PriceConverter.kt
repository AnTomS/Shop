package com.atom.shop.data.converter

import androidx.room.TypeConverter
import com.atom.shop.domain.dto.PriceEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PriceConverter {

    @TypeConverter
    fun fromString(value: String?): PriceEntity? {
        return value?.let { Gson().fromJson(it, object : TypeToken<PriceEntity>() {}.type) }
    }

    @TypeConverter
    fun toString(value: PriceEntity?): String? {
        return value?.let { Gson().toJson(it) }
    }
}