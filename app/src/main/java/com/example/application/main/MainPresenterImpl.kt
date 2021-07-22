package com.example.application.main

import android.util.Log
import com.example.application.base.AbsBasePresenterImpl
import com.example.application.base.BasePresenter

class MainPresenterImpl(view: Contract.MainView) : AbsBasePresenterImpl<Contract.MainView>(view), BasePresenter<Contract.MainView> {
    private val tag = javaClass.name

    fun loadData() {
        getView().showLoading()
        val data = mutableListOf<Int>()
        for (i in 1 .. 100) {
            Thread.sleep(100)
            data.add(i)
        }
        getView().onSuccess(data)
        getView().hideLoading()
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(tag, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e(tag, "onResume")
    }

    override fun onDestory() {
        super.onDestory()
        Log.e(tag, "onDestory")
    }

}