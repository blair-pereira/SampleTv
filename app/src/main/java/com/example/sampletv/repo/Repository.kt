package com.example.sampletv.repo

import com.example.sampletv.model.ShowItemModel
import com.example.sampletv.room.ShowsEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {


    suspend fun getShows(query :String?):Response<ShowItemModel>
//    suspend fun insertIntoDb(showEntity: ShowsEntity)
//    fun getDataFromDb(): Flow<List<ShowsEntity>>

}