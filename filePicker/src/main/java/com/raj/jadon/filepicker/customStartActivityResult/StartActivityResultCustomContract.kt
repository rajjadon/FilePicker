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
    lateinit var setCameraCompressedImageLauncher: ActivityResultLauncher<Intent>
    lateinit var setGalleryLauncher: ActivityResultLauncher<Intent>
    lateinit var setDocLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        setCameraLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.CAPTURE_ORIGINAL_IMAGE.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setCamera
            )

        setCameraCompressedImageLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.CAPTURE_COMPRESSED_IMAGE.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setCameraCompressed
            )

        setDocLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.DOC.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setDOC
            )

        setGalleryLauncher =
            resultRegistry.register(
                StartActivityForResultEnum.GALLERY.name,
                owner,
                ActivityResultContracts.StartActivityForResult(),
                onResultManager::setGalleryResult
            )

    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        setCameraLauncher.unregister()
        setGalleryLauncher.unregister()
        setDocLauncher.unregister()
        owner.lifecycle.removeObserver(this)
    }
}