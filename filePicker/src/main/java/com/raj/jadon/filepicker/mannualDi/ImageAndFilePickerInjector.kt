package com.raj.jadon.filepicker.mannualDi

import android.content.Context
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultContractListenerImplement
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.imageAndFilePicker.ImageAndFilePicker
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract


class ImageAndFilePickerInjector private constructor() {

    companion object {
        private lateinit var instance: ImageAndFilePickerContract

        fun initInjectorInstance(context: Context) {
            if (!this::instance.isInitialized) {
                instance = ImageAndFilePicker(
                    context,
                    StartActivityResultCustomContract(StartActivityResultContractListenerImplement())
                )
            }
        }

        @Throws(Exception::class)
        fun getInstance() =
            if (!this::instance.isInitialized) throw Exception("Please initialize imageAndFilePicker the injector") else instance
    }
}