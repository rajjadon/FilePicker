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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        Injector.getInstance().startActivityContracts.resultRegistry =
            activityResultRegistry
        lifecycle.addObserver(Injector.getInstance().startActivityContracts)

        Injector.getInstance().startActivityContracts.onResultManager.startActivityCustomOnResult =
            this

        mainBinding.imageAndFilePicker = Injector.getInstance().imageAndFilePicker
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(Injector.getInstance().startActivityContracts)
    }

    override fun onResult(resultECode: StartActivityForResultEnum, result: ActivityResult) {
        result.data?.let { it ->

            val imageUri =
                Injector.getInstance().imageAndFilePicker.getDataFromActivityResult(
                    resultECode,
                    it
                )
            Timber.e(imageUri)
            imageUri?.let { uri ->
                mainBinding.imageView.setImageURI(uri.toUri())
            }
        }
    }
}