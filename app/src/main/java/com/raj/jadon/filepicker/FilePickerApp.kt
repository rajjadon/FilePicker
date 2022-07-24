package com.raj.jadon.filepicker

import android.app.Application
import com.raj.jadon.filepicker.mannualDi.Injector
import timber.log.Timber

class FilePickerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.initInjectorInstance(this)
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}