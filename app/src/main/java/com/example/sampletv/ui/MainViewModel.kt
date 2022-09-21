package com.example.sampletv.ui

import androidx.lifecycle.ViewModel
import com.example.sampletv.repo.Repository
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

    fun getShows(showName: String?) {
        _data.value = (UiState.loading)
        CoroutineScope(Dispatchers.IO).launch {

            var response = repository.getShows(showName)
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

}