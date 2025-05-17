package com.example.lessons11.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class CityConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCity(city: City): String {
        return gson.toJson(city)
    }

    @TypeConverter
    fun toCity(data: String): City {
        return gson.fromJson(data, City::class.java)
    }
}
