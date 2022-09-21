package com.example.sampletv.model


import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("medium")
    val medium: String? = "",
    @SerializedName("original")
    val original: String? = ""
)