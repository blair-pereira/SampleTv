package com.example.sampletv.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sampletv.room.ShowsTypeConverter
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "Shows")
data class ShowItemModel(
    @SerializedName("averageRuntime")
    val averageRuntime: Int? = 0,
    @SerializedName("ended")
    val ended: String? = "",
    //@PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: ImageModel? = ImageModel(),
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("officialSite")
    val officialSite: String? = "",
    @SerializedName("premiered")
    val premiered: String? = "",
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("updated")
    val updated: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("weight")
    val weight: Int? = 0
)