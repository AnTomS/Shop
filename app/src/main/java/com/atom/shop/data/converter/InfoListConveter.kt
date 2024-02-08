package com.atom.shop.data.converter

import androidx.room.TypeConverter
import com.atom.shop.domain.dto.InfoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InfoListConverter{

    @TypeConverter
    fun fromString(value: String?): List<InfoEntity>? {
        return value?.let { Gson().fromJson(it, object : TypeToken<List<InfoEntity>>() {}.type) }
    }

    @TypeConverter
    fun toString(value: List<InfoEntity>?): String? {
        return value?.let { Gson().toJson(it) }
    }
}