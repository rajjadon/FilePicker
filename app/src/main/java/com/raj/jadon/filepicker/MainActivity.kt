package com.raj.jadon.filepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raj.jadon.filepicker.customStartActivityResult.contract.StartActivityContracts
import com.raj.jadon.filepicker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var startActivityContracts: StartActivityContracts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
}