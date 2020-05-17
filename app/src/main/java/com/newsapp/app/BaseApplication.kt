package com.newsapp.app

import com.newsapp.app.di.DaggerAppComponent
import com.newsapp.app.di.RoomModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
       return DaggerAppComponent.builder().application(this)!!.build()
    }
    
    override fun onLowMemory() {
        super.onLowMemory()
        System.gc()
    }
}
