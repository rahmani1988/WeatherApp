package com.reza.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Trace.beginSection("configureOnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Trace.endSection()
    }
}