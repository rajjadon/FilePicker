/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/08/21, 6:50 PM
 */

package com.raj.jadon.filepicker.customStartActivityResult

import androidx.activity.result.ActivityResult
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityContracts
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult

class StartActivityResultContractListenerImplement : StartActivityContracts {

    override lateinit var startActivityCustomOnResult: StartActivityCustomOnResult

    override fun setCamera(result: ActivityResult) {

        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(
                StartActivityForResultEnum.CAMERA_WITHOUT_COMPRESSION,
                result
            )
    }

    override fun setCameraCompressed(result: ActivityResult) {
        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(
                StartActivityForResultEnum.CAMERA_WITH_COMPRESSION,
                result
            )
    }

    override fun setDOC(result: ActivityResult) {
        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(StartActivityForResultEnum.DOC, result)
    }

    override fun setGalleryResult(result: ActivityResult) {

        if (this::startActivityCustomOnResult.isInitialized)
            startActivityCustomOnResult.onResult(
                StartActivityForResultEnum.GALLERY,
                result
            )
    }
}