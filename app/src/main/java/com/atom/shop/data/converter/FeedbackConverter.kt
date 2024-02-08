package com.atom.shop.data.converter

import androidx.room.TypeConverter
import com.atom.shop.domain.dto.FeedbackEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FeedbackConverter {
    @TypeConverter
    fun fromString(value: String?): FeedbackEntity? {
        return value?.let { Gson().fromJson(it, object : TypeToken<FeedbackEntity>() {}.type) }
    }

    @TypeConverter
    fun toString(value: FeedbackEntity?): String? {
        return value?.let { Gson().toJson(it) }
    }
}
