package com.raj.jadon.filepicker.mannualDi

import android.annotation.SuppressLint
import android.content.Context
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultContractListenerImplement
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.imageAndFilePicker.ImageAndFilePicker

class Injector private constructor(private val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var injector: Injector? = null

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var startActivityContracts: StartActivityResultCustomContract? = null

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var imageAndFilePicker: ImageAndFilePicker? = null

        fun initInjectorInstance(context: Context) = run {
            if (injector == null) {
                injector = Injector(context)
                startActivityContracts =
                    StartActivityResultCustomContract(StartActivityResultContractListenerImplement())
                startActivityContracts?.let {
                    imageAndFilePicker = ImageAndFilePicker(context, it)
                }
            }
        }

        @Throws(Exception::class)
        fun getInjectorInstance(): Injector {
            return injector
                ?: kotlin.run { throw Exception(Throwable("Injector is not initialized")) }
        }
    }

    fun getImageFilePicker() = imageAndFilePicker

    fun getStartActivityContract() = startActivityContracts
}
