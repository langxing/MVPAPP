package com.example.application.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbsBaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity() {

    abstract fun getPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = getPresenter()
        presenter.setLifeCycle(this)
        presenter.setContext(applicationContext)
    }
}