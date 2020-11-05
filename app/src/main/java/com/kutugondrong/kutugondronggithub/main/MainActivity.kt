package com.kutugondrong.kutugondronggithub.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kutugondrong.kutugondronggithub.R
import com.kutugondrong.kutugondronggithub.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
