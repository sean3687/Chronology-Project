package com.tassiecomp.mychronology.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.ui.MainActivity
import com.tassiecomp.mychronology.ui.MainViewModel

class WeekReportFragment: Fragment(R.layout.fragment_weekreport) {
    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

}