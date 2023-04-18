package com.reza.weatherapp.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleFrameBenchmark {
    @get:Rule
    val rule = MacrobenchmarkRule()

    @Test
    fun frame() = rule.measureRepeated(
        packageName = "com.reza.weatherapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {

        }
    ) {
        pressHome()
        startActivityAndWait() // Time To Initial Drawn (TTID)

        device.wait(Until.hasObject(By.text("Hello World!")), 30_000) // Time To Fully Drawn (TTFD)
    }
}