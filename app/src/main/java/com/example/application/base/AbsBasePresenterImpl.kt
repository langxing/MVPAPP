package com.example.application.base

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

@Suppress("LeakingThis")
abstract class AbsBasePresenterImpl<V : BaseView>(private val view: V) : BasePresenter<V>, LifecycleObserver {
    private lateinit var mContext: WeakReference<Context>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestory() {
        mContext.clear()
    }

    override fun getView(): V = view

    override fun setLifeCycle(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun setContext(context: Context) {
        mContext = WeakReference(context)
    }

    override fun getContext(): Context = mContext.get()!!

}