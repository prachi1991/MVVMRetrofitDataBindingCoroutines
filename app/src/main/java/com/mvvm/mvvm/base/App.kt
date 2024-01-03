package com.mvvm.mvvm.base

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(LocaleManager.setLocale(base))
//        MultiDex.install(this)
//    }


}
