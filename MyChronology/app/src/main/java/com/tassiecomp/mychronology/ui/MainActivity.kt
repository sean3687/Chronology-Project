package com.tassiecomp.mychronology.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.App.Companion.viewModel
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var MainViewModelHomeFragment:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainViewModelHomeFragment = ViewModelProvider(this).get(MainViewModel::class.java)

        bottom_navigation.setupWithNavController(nav_host_fragment.findNavController())




    }




}

