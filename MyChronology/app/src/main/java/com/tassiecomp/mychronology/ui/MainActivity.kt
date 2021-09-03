package com.tassiecomp.mychronology.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tassiecomp.mychronology.R



class MainActivity : AppCompatActivity() {

    lateinit var MainViewModelHomeFragment:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)
            }




}


