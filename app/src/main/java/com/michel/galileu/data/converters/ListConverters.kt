package com.michel.galileu.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListConverters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            val type: Type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, type) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}