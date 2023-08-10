package com.reza.core.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.reza.core.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    abstract fun registerView()
    abstract fun setupUi()
    abstract fun setupSubscribers()
    abstract fun setupListeners()

    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        // calling other functions
        registerView()
        setupUi()
        setupListeners()
        setupSubscribers()
    }
}