package com.maximillianleonov.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximillianleonov.weatherapp.databinding.ActivityMainBinding
import com.maximillianleonov.weatherapp.util.unsafeLazy

class MainActivity : AppCompatActivity() {
    private val binding by unsafeLazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
