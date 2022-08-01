/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/08/21, 7:15 PM
 */

package com.raj.jadon.filepicker.imageAndFilePicker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.activity.result.ActivityResultRegistry
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.raj.jadon.filepicker.R
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ImageAndFilePicker constructor(
    private val context: Context,
    private val startActivityContracts: StartActivityResultCustomContract
) :
    ImageAndFilePickerContract {

    lateinit var photoFile: File
    private var compressPercentage: Int = 0

    override fun openGallery() {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        photoFile = createImageFile(context)
                        val pickPhoto = Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        startActivityContracts.setGalleryLauncher.launch(pickPhoto)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?,
                ) {
                    token!!.continuePermissionRequest()
                }
            }).check()
    }

    override fun pickDOCFile() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "application/*" // application/pdf"
        startActivityContracts.setDocLauncher.launch(
            Intent.createChooser(
                intent,
                "Select Document file"
            )
        )
    }

    override fun openCamera(compressPercentage: Int) {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {

                        this@ImageAndFilePicker.compressPercentage = compressPercentage
                        photoFile = createImageFile(context)

                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)

                        takePictureIntent.let {
                            startActivityContracts.setCameraCompressedImageLauncher.launch(
                                takePictureIntent
                            )
                        }

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?,
                ) {
                    token!!.continuePermissionRequest()
                }
            }).check()
    }

    override fun openCamera() {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {

                        photoFile = createImageFile(context)

                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        takePictureIntent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            getFileProviderFileUri()
                        )
                        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)

                        takePictureIntent.let {
                            startActivityContracts.setCameraLauncher.launch(takePictureIntent)
                        }

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?,
                ) {
                    token!!.continuePermissionRequest()
                }
            }).check()
    }

    private fun getFileProviderFileUri(): Uri = kotlin.run {
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            photoFile
        )
    }

    override fun getDataFromActivityResult(
        resultECode: StartActivityForResultEnum,
        result: Intent,
        fragment: Fragment?,
        isCroppingEnable: Boolean
    ): String? {
        var currentPhotoPath: String? = ""

        when (resultECode) {
            StartActivityForResultEnum.GALLERY -> {

                currentPhotoPath = if (isCroppingEnable)
                    fragment?.let {
                        launchImageCropping(
                            File(
                                getPicturePathForGallery(
                                    context,
                                    result
                                )
                            ).toUri(),
                            it
                        ).toString()
                    }
                else
                    getPicturePathForGallery(context, result)
            }

            StartActivityForResultEnum.CAMERA_WITH_COMPRESSION -> {
                try {
                    saveBitmapIntoFIle(result.extras?.get("data") as Bitmap)
                    currentPhotoPath = if (isCroppingEnable)
                        fragment?.let {
                            launchImageCropping(photoFile.toUri(), it).toString()
                        }
                    else
                        photoFile.toUri().toString()

                    Timber.e("onResult: IMAGE_PATH Camera- $currentPhotoPath")
                } catch (e: Exception) {
                    Timber.e("onFailure: ${e.message}")
                }
            }

            StartActivityForResultEnum.DOC -> {
                val uri: Uri = result.data!!
                currentPhotoPath = uri.toString()
                Timber.d("getImageFromActivityResult: DOC - $currentPhotoPath")
            }

            StartActivityForResultEnum.IMAGE_CROPPING -> {
                currentPhotoPath = UCrop.getOutput(result)?.path.toString()
                Timber.tag("crop Image uri").e(currentPhotoPath)
            }
            else -> {}
        }
        return currentPhotoPath
    }

    override fun getCameraImageFile(): File {
        return if (this::photoFile.isInitialized)
            photoFile
        else
            throw Exception("Please open camera before calling this function getCameraImageFile()")
    }

    override fun registerResultRegistry(
        onResult: StartActivityCustomOnResult,
        lifecycle: Lifecycle,
        activityResultRegistry: ActivityResultRegistry
    ) {
        startActivityContracts.resultRegistry = activityResultRegistry
        startActivityContracts.onResultManager.startActivityCustomOnResult = onResult
        lifecycle.addObserver(startActivityContracts)
    }

    private fun launchImageCropping(imageUri: Uri, activity: Fragment) {

        val uCropOption = UCrop.Options()
        uCropOption.setCircleDimmedLayer(true)
        uCropOption.setAllowedGestures(UCropActivity.SCALE, UCropActivity.NONE, UCropActivity.NONE)
        uCropOption.setShowCropGrid(false)
        uCropOption.setShowCropFrame(false)
        uCropOption.setHideBottomControls(true)
        uCropOption.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        uCropOption.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        uCropOption.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.colorOnPrimary))
        uCropOption.setToolbarTitle("Drag and zoom image")

        UCrop.of(imageUri, photoFile.toUri())
            .withOptions(uCropOption)
            .start(context, activity)
    }

    private fun getPicturePathForGallery(context: Context, result: Intent): String {
        val selectedImage: Uri = result.data!!
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = context.contentResolver.query(
            selectedImage,
            filePathColumn, null, null, null
        )!!
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()

        return picturePath
    }

    private fun createImageFile(context: Context): File {
        val imageFileName =
            "IMG_" + SimpleDateFormat("yyyyMMdd_HH_mm_ss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    private fun saveBitmapIntoFIle(bitmap: Bitmap) {

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressPercentage /*ignored for PNG*/, bos)
        val bitmapdata = bos.toByteArray()

        //write the bytes in file
        val fos = FileOutputStream(photoFile)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
    }

    @SuppressLint("Range", "Recycle")
    override fun getFileName(fileUri: Uri): String? {
        var displayName: String? = null
        if (fileUri.toString().startsWith("content://")) {
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver
                    .query(fileUri, null, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    displayName =
                        cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        } else if (fileUri.toString().startsWith("file://")) {
            displayName = File(fileUri.toString()).name
        }

        return displayName
    }
}
