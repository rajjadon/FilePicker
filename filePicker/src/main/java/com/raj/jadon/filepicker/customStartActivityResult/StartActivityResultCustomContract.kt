/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/08/21, 6:49 PM
 */

package com.raj.jadon.filepicker.customStartActivityResult

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityContracts


class StartActivityResultCustomContract(val onResultManager: StartActivityContracts) :
    DefaultLifecycleObserver, LifecycleObserver {

    lateinit var resultRegistry: ActivityResultRegistry

    //Contract resultRegistry object
    lateinit var setCameraLauncher: ActivityResultLauncher<Intent>
    lateinit var setGalleryLauncher: ActivityResultLauncher<Intent>
    lateinit var setPdfLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        setCameraLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.CAMERA.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setCamera
            )

        setPdfLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.PDF.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setPdf
            )

        setGalleryLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.GALLERY.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setGalleryResult
            )

    }
}