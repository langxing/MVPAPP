package com.example.application.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface BasePresenter<V : BaseView> {

    fun getView(): V

    fun setLifeCycle(lifecycleOwner: LifecycleOwner)

    fun setContext(context: Context)

    fun getContext(): Context

}