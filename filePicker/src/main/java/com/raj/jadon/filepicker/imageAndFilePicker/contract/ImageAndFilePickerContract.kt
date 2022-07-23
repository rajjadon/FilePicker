package com.raj.jadon.filepicker.imageAndFilePicker.contract

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract

interface ImageAndFilePickerContract {

    fun openGallery(
        context: Context,
        startActivityResultCustomContract: StartActivityResultCustomContract,
    )

    fun openCamera(
        context: Context,
        startActivityResultCustomContract: StartActivityResultCustomContract,
    )

    fun pickPDFFile(
        context: Context,
        startActivityResultCustomContract: StartActivityResultCustomContract,
    )

    fun getDataFromActivityResult(
        context: Context,
        resultECode: StartActivityForResultEnum,
        result: Intent,
        activity: Fragment,
        isCroppingEnable: Boolean = false,
    ): String

    fun getFileName(context: Context, fileUri: Uri): String?
}