package com.raj.jadon.filepicker

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityForResultEnum
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityCustomOnResult
import com.raj.jadon.filepicker.databinding.ActivityMainBinding
import com.raj.jadon.filepicker.mannualDi.Injector
import timber.log.Timber

class MainActivity : AppCompatActivity(), StartActivityCustomOnResult {
    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var injector: Injector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        try {
            injector = Injector.getInjectorInstance()

            injector.getStartActivityContract()?.let {
                it.resultRegistry = activityResultRegistry
                lifecycle.addObserver(it)
                it.onResultManager.startActivityCustomOnResult = this
            }
        } catch (e: Exception) {
            Timber.e(e.message)
        }

        setOnClickListener()
    }

    private fun setOnClickListener() {
        mainBinding.openCamera.setOnClickListener {
            injector.getImageFilePicker()?.openCamera()
        }

        mainBinding.openGallery.setOnClickListener { injector.getImageFilePicker()?.openGallery() }
    }

    override fun onDestroy() {
        super.onDestroy()
        injector.getStartActivityContract()?.let {
            lifecycle.removeObserver(it)
        }
    }

    override fun onResult(resultECode: StartActivityForResultEnum, result: ActivityResult) {
        result.data?.let { it ->

            injector.getImageFilePicker()?.let { imageAndFilePicker ->
                val imageUri = imageAndFilePicker.getDataFromActivityResult(resultECode, it)
                Timber.e(imageUri)
                imageUri?.let { uri ->
                    mainBinding.imageView.setImageURI(uri.toUri())
                }
            }
        }
    }
}