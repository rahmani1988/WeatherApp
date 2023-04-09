package com.reza.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val x = 11
        val d = 12
        setContentView(R.layout.activity_main)
    }
}