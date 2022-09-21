package com.example.sampletv.repo

import android.util.Log
import com.example.sampletv.api.ApiDetails
import com.example.sampletv.model.ShowItemModel
import com.example.sampletv.room.ShowsDao
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val showDao: ShowsDao,
    val apiDetails: ApiDetails
) : Repository {

    override suspend fun getShows(query: String?): Response<ShowItemModel> {
        val localData = showDao.searchDatabase(query!!)
        if (localData != null) {
            Log.e("Output", "data coming from database")
            return Response.success(localData)
        }
        val output = apiDetails.getTvShows(query)
        if (output.isSuccessful) {
            showDao.insertIntoDb(output.body()!!)
        }
        Log.e("Output", "data coming from api")
        return output
    }


    override suspend fun insertIntoDb(showItemModel: ShowItemModel) {
        showDao.insertIntoDb(showItemModel)
    }

    override fun getDataFromDb(): Flow<List<ShowItemModel>> =
        showDao.getDataFromDb()
}