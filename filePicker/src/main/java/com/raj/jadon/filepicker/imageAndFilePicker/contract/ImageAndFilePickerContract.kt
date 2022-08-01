package com.raj.jadon.filepicker.imageAndFilePicker.contract

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultRegistry
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import java.io.File

interface ImageAndFilePickerContract {

    fun openGallery()

    fun openCamera()

    fun pickDOCFile()

    fun getDataFromActivityResult(
        resultECode: StartActivityForResultEnum,
        result: Intent,
        fragment: Fragment? = null,
        isCroppingEnable: Boolean = false,
    ): String?

    @Throws(Exception::class)
    fun getCameraImageFile(): File

    fun getFileName(fileUri: Uri): String?

    fun registerResultRegistry(
        onResult: StartActivityCustomOnResult,
        lifecycle: Lifecycle,
        activityResultRegistry: ActivityResultRegistry
    )
}