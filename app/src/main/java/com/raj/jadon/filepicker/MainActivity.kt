package com.raj.jadon.filepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import com.raj.jadon.filepicker.databinding.ActivityMainBinding
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(startActivityContracts)
    }
}