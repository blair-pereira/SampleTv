package com.example.sampletv.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampletv.R
import com.example.sampletv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mainFragment = MainFragment()


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_View, mainFragment)
                .commitAllowingStateLoss()

        }

    }


}