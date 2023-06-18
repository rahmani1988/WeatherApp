package com.reza.weatherapp.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun registerView()
    abstract fun setupUi()
    abstract fun setupSubscribers()
    abstract fun setupListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerView()
        setupUi()
        setupListeners()
        setupSubscribers()
    }
}