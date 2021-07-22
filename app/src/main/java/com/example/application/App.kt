package com.example.application

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App : Application(), ViewModelStoreOwner {
    private lateinit var viewModelStore: ViewModelStore

    override fun onCreate() {
        super.onCreate()
        viewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = viewModelStore
}