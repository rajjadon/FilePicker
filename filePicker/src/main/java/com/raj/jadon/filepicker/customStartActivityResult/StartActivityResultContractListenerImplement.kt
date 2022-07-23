/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/08/21, 6:50 PM
 */

package com.raj.jadon.filepicker.customStartActivityResult

import androidx.activity.result.ActivityResult
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityContracts
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartActivityResultContractListenerImplement @Inject constructor(): StartActivityContracts {

    override lateinit var startActivityCustomOnResult: StartActivityCustomOnResult

    override fun setCamera(result: ActivityResult) {

        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(StartActivityForResultEnum.CAMERA, result)
    }

    override fun setPdf(result: ActivityResult) {
        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(StartActivityForResultEnum.PDF, result)
    }

    override fun setGalleryResult(result: ActivityResult) {

        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(
                StartActivityForResultEnum.GALLERY,
                result
            )
    }
}