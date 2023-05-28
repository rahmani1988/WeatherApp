package com.reza.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialise add mob
        MobileAds.initialize(this) {}
    }
}