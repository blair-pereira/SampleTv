package com.example.sampletv.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sampletv.model.ShowItemModel

//@Database(entities = [ShowItemModel::class],version = 1 , exportSchema = false)
//@TypeConverters(ShowsTypeConverter::class)
abstract class ShowsDatabase :RoomDatabase() {
    abstract fun showsDao()
}