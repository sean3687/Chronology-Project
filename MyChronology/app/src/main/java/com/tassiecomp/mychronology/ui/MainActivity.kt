package com.tassiecomp.mychronology.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_home_cardview.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        create_new_subject.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.create_subject_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()


        }
    }




}