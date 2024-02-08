package com.atom.shop.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }
    }

    @TypeConverter
    fun toString(value: List<String>?): String? {
        return value?.let { Gson().toJson(it) }
    }
}