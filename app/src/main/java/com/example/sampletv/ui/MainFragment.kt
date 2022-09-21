package com.example.sampletv.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampletv.R
import com.example.sampletv.databinding.FragmentMainBinding
import com.example.sampletv.model.ShowItemModel
import com.example.sampletv.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel by viewModels<MainViewModel>()
        binding = FragmentMainBinding.bind(view)
        view.hideKeyboard()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                view.hideKeyboard()
                viewmodel.getShows(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

      // viewmodel.readShows.observe(requireActivity()){

       //}

        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.data.collect() { state ->
                when (state) {
                    is UiState.loading -> {
                        Log.d("API Response: ", "LOADING")

                    }
                    is UiState.error -> {
                        Log.d("API Response: ", "Error -> ${state.error}")

                    }
                    is UiState.success<*> -> {
                        val response = state.response as ShowItemModel
                        updateUI(response)
                    }
                }
            }
        }


    }



    private fun updateUI(response: ShowItemModel) {
        binding.showNameId.text = response.name
        val premieredDate = response.premiered

        binding.premieredDays.text = premieredDate?.let { getDatesDifference(it) }

        context?.let {
            Glide.with(it).load(response.image?.medium)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(binding.imageUrlId)
        }

    }

    fun getDatesDifference(date:String):String{
        lateinit var PeriodDifference :String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val from = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            // get today's date
            val today = LocalDate.now()
            // calculate the period between those two
            var period = Period.between(from, today)
            PeriodDifference = "Last Premiered : ${period.years} Years - ${period.months} Months and ${period.days} days"

        }
        return PeriodDifference
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


}