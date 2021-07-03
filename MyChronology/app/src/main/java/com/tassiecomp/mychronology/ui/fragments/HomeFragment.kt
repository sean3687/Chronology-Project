package com.tassiecomp.mychronology.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.ui.MainActivity
import com.tassiecomp.mychronology.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
//    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as MainActivity).viewModel

        create_new_subject.setOnClickListener{
            val mDialogView = LayoutInflater.from(MainActivity).inflate(R.layout.create_subject_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(MainActivity)
                .setView(mDialogView)
                .setTitle("Create Subject")
            //show dialog
                val  mAlertDialog = mBuilder.show()
        }
    }

}