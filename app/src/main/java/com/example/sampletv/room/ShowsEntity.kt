package com.example.sampletv.room

import androidx.room.PrimaryKey
import com.example.sampletv.model.ShowItemModel

class ShowsEntity(val showItemModel: ShowItemModel) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}