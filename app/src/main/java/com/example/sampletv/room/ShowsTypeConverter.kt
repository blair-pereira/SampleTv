package com.example.sampletv.room

import androidx.room.TypeConverter
import com.example.sampletv.model.ImageModel
import com.example.sampletv.model.ShowItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShowsTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun imageModelToString(shows: ImageModel): String = gson.toJson(shows)

    @TypeConverter
    fun stringToImageModel(data: String): ImageModel {
        val listType = object : TypeToken<ImageModel>() {}.type
        return gson.fromJson(data, listType)
    }
}