package com.tassiecomp.mychronology.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tassiecomp.mychronology.R

import com.tassiecomp.mychronology.ui.viewModel.MainViewModel

class ChronologyFragment: Fragment(R.layout.fragment_chronology) {
    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}