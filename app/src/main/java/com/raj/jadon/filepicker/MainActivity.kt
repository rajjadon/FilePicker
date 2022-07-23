package com.raj.jadon.filepicker

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import com.raj.jadon.filepicker.databinding.ActivityMainBinding
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StartActivityCustomOnResult {
    private lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var startActivityContracts: StartActivityResultCustomContract

    @Inject
    lateinit var imageAndFilePicker: ImageAndFilePickerContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        startActivityContracts.resultRegistry = activityResultRegistry
        lifecycle.addObserver(startActivityContracts)
        startActivityContracts.onResultManager.startActivityCustomOnResult = this

        setOnClickListener()
    }

    private fun setOnClickListener() {
        mainBinding.openCamera.setOnClickListener { imageAndFilePicker.openCamera() }

        mainBinding.openGallery.setOnClickListener { imageAndFilePicker.openGallery() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(startActivityContracts)
    }

    override fun onResult(resultECode: StartActivityForResultEnum, result: ActivityResult) {
        result.data?.let {
            Timber.e(imageAndFilePicker.getDataFromActivityResult(resultECode, it))
        }
    }
}