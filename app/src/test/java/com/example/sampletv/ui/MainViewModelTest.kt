package com.example.sampletv.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.sampletv.model.ImageModel
import com.example.sampletv.model.ShowItemModel
import com.example.sampletv.repo.Repository
import com.example.sampletv.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response


class MainViewModelTest {

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun`Given api data when response When Error then return error`()= runBlocking{
        whenever(repository.getShows("Girls")).thenReturn(Response.error(404,"Error ".toResponseBody()))
        viewModel.getShows("Girls")
        viewModel.data.asLiveData().observeForever {
            assertEquals((it as UiState.error).error,"")
        }
    }

    @Test
    fun`Given api data when response When Success then return all data`()= runBlocking{

        val showData = ShowItemModel(averageRuntime = 1, ended = "", id = 234, image = ImageModel("", original = ""), language = "",
        name = "Girls", officialSite = "", premiered = "", runtime = 0, status = "", summary = "",
        type = "", updated = 1, url = "", weight = 1)

        Mockito.`when`(repository.getShows("Girls")).thenReturn(Response.success(showData))
        viewModel.getShows("Girls")
        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UiState.success<*>).response,showData)
        }
    }
}