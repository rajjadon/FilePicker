package com.raj.jadon.filepicker

import android.app.Application
import com.raj.jadon.filepicker.mannualDi.ImageAndFilePickerInjector
import timber.log.Timber

class FilePickerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ImageAndFilePickerInjector.initInjectorInstance(this)
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}