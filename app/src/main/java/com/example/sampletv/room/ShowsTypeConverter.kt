package com.example.sampletv.room

import androidx.room.TypeConverter
import com.example.sampletv.model.ShowItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowsTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun userToString(shows: ShowItemModel): String = gson.toJson(shows)

    @TypeConverter
    fun stringToUser(data: String): ShowItemModel {
        val listType = object : TypeToken<ShowItemModel>() {}.type
        return gson.fromJson(data, listType)
    }
}