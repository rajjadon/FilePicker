package com.raj.jadon.filepicker

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import com.raj.jadon.filepicker.databinding.ActivityMainBinding
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract
import com.raj.jadon.filepicker.mannualDi.ImageAndFilePickerInjector
import timber.log.Timber

class MainActivity : AppCompatActivity(), StartActivityCustomOnResult {
    private lateinit var mainBinding: ActivityMainBinding

    private val imageAndFilePicker: ImageAndFilePickerContract by lazy { ImageAndFilePickerInjector.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        imageAndFilePicker.registerResultRegistry(
            onResult = this,
            lifecycle = lifecycle,
            activityResultRegistry = activityResultRegistry
        )

        mainBinding.imageAndFilePicker = imageAndFilePicker
    }

    override fun onResult(resultECode: StartActivityForResultEnum, result: ActivityResult) {
        result.data?.let { it ->

            val imageUri =
                imageAndFilePicker.getDataFromActivityResult(
                    resultECode,
                    it
                )
            Timber.e(imageUri)
            imageUri?.let { uri ->
                mainBinding.imageView.setImageURI(uri.toUri())
            }
        }

        if (resultECode == StartActivityForResultEnum.CAMERA_WITHOUT_COMPRESSION) {
            mainBinding.imageView.setImageURI(imageAndFilePicker.getCameraImageFile().toUri())
        }
    }
}