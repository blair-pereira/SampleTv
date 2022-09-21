package com.example.sampletv.util

sealed class UiState {
    object loading:UiState()
    class success<T>(val response :T):UiState()
    class error(val error:String):UiState()
}