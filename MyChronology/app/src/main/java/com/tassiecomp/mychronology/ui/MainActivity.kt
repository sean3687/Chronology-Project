package com.tassiecomp.mychronology.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_home_cardview.*

class MainActivity : AppCompatActivity() {

//    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val mainRepository = MainRepository(HomeGradeDB(this))
//        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
//        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)


        bottom_navigation.setupWithNavController(nav_host_fragment.findNavController())

//        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener { //
//            override fun onStartTrackingTouch(slider: Slider) {
//                // Responds to when slider's touch event is being started
//            }
//
//            override fun onStopTrackingTouch(slider: Slider) {
//                // Responds to when slider's touch event is being stopped
//            }
//        })
//
//        slider.addOnChangeListener { slider, value, fromUser ->
//            // Responds to when slider's value is changed
//        }

    }




}