package com.reza.weatherapp.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setupUi()
    abstract fun setupSubscribers()
    abstract fun setupListeners()

    override fun onStart() {
        super.onStart()
        setupUi()
        setupListeners()
        setupSubscribers()
    }
}