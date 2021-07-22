package com.example.application

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider

class SecondActivity : AppCompatActivity() {
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mMainViewModel = ViewModelProvider(application as App, getFactory()).get(MainViewModel::class.java)
        mMainViewModel.sex.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val sex = if (mMainViewModel.sex.get() == 1) "男" else "女"
                Toast.makeText(applicationContext, sex, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getFactory(): ViewModelProvider.AndroidViewModelFactory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
}