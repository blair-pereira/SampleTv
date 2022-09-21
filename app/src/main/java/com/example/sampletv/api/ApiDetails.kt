package com.example.sampletv.api

import com.example.sampletv.model.ShowItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDetails {


    @GET("singlesearch/shows")
    suspend fun getTvShows(@Query("q") q: String?):Response<ShowItemModel>


}