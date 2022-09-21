package com.example.sampletv.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampletv.model.ShowItemModel
import com.example.sampletv.repo.Repository
import com.example.sampletv.room.ShowsDao
import com.example.sampletv.room.ShowsEntity
import com.example.sampletv.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _data: MutableStateFlow<UiState> = MutableStateFlow(UiState.loading)
    val data: StateFlow<UiState> get() = _data
    //var data_DB = listOf<ShowsEntity>()

    fun getShows(showName: String?) {
        _data.value = (UiState.loading)
        CoroutineScope(Dispatchers.IO).launch {

            var response = repository.getShows(showName)
            Log.d("API Response", "$response")
            if (response.isSuccessful) {
                _data.value = (
                        response.body()?.let { success ->
                            //insertIntoDatabase(success)
                            UiState.success(success)
                        }!!
                        )
            } else {
                _data.value = (
                        UiState.error("")
                        )
            }

        }
    }

    //val readShows: LiveData<List<ShowsEntity>> = repository.getDataFromDb().asLiveData()

//    fun insertIntoDatabase(model: ShowItemModel){
//        val gitEntity = ShowsEntity(model)
//        CoroutineScope(Dispatchers.IO).launch {
//            repository.insertIntoDb(gitEntity)
//        }
//    }



}