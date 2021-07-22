package com.example.application

import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val sex = ObservableInt()

    fun sendData(num: Int = 0) {
        Thread.sleep(200)
        sex.set(num)
    }
}