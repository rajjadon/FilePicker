package com.raj.jadon.filepicker.mannualDi

import android.content.Context
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultContractListenerImplement
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.imageAndFilePicker.ImageAndFilePicker
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract


class Injector private constructor() {

    val imageAndFilePicker: ImageAndFilePickerContract
        get() = imageAndFilePickerContract

    val startActivityContracts: StartActivityResultCustomContract
        get() = startActivityResultCustomContract

    companion object {
        private lateinit var startActivityResultCustomContract: StartActivityResultCustomContract
        private lateinit var imageAndFilePickerContract: ImageAndFilePickerContract

        private lateinit var instance: Injector

        fun initInjectorInstance(context: Context) {
            if (!this::instance.isInitialized) {
                instance = Injector()
                startActivityResultCustomContract =
                    StartActivityResultCustomContract(StartActivityResultContractListenerImplement())
                imageAndFilePickerContract = ImageAndFilePicker(
                    context,
                    startActivityResultCustomContract
                )
            }
        }

        @Throws(Exception::class)
        fun getInstance() =
            if (!this::instance.isInitialized) throw Exception("Please init the injector") else instance
    }
}