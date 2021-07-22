package com.example.application.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class AbsBaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment() {

    abstract fun getPresenter(): P

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val presenter = getPresenter()
        presenter.setLifeCycle(viewLifecycleOwner)
        context?.apply {
            presenter.setContext(applicationContext)
        }
    }
}