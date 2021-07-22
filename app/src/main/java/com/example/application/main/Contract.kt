package com.example.application.main

import com.example.application.base.BasePresenter
import com.example.application.base.BaseView

interface Contract {
    interface MainView : BaseView {
        fun onComplete()
        fun onSuccess(data: List<Int>)
        fun onError(code: Int, msg: String)
    }

    interface MainPresenter : BasePresenter<MainView>
}