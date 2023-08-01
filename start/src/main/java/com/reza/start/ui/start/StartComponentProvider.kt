package com.reza.start.ui.start

import com.reza.start.ui.start.StartComponent

interface StartComponentProvider {
    fun provideStartComponent(): StartComponent
}