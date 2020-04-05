package com.animescrap

import android.app.Application
import com.animescrap.core.di.module.addModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext() = instance as MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(addModule)
        }
    }
}