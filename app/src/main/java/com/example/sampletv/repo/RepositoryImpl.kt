package com.example.sampletv.repo

import com.example.sampletv.api.ApiDetails
import com.example.sampletv.model.ShowItemModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    //val showDao: ShowsDao,
    val apiDetails: ApiDetails
) : Repository {

    override suspend fun getShows(query: String?): Response<ShowItemModel> {
        //if(inDB) // show local roomDB.getTvShows(query)
        //  else apiDetails.getTvShows(query)
        return apiDetails.getTvShows(query)
    }


//    override suspend fun insertIntoDb(showEntity: ShowsEntity) {
//        showDao.insertIntoDb(showEntity)
//    }
//
//    override fun getDataFromDb(): Flow<List<ShowsEntity>> =
//        showDao.getDataFromDb()


}