package com.example.sampletv.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampletv.model.ShowItemModel
import kotlinx.coroutines.flow.Flow

//@Dao
interface ShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoDb(showsEntity: ShowsEntity)

    @Query("Select * from Shows")
    fun getDataFromDb(): Flow<List<ShowsEntity>>

}