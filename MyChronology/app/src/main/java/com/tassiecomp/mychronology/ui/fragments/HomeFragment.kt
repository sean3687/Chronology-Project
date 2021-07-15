package com.tassiecomp.mychronology.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.repository.MainRepository
import com.tassiecomp.mychronology.ui.MainActivity
import com.tassiecomp.mychronology.ui.MainViewModel
import com.tassiecomp.mychronology.ui.MainViewModelProviderFactory
import com.tassiecomp.mychronology.util.CreateSubjectDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        create_new_subject.setOnClickListener() {
            showDialog()
            Log.d("TAGG","create subject clicked")

        }



    }


    fun showDialog() {
        val fragmentManager = getActivity()?.supportFragmentManager
        val newFragment = CreateSubjectDialogFragment()
        val transaction = fragmentManager!!.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction
            .add(android.R.id.content, newFragment)
            .addToBackStack(null)
            .commit()
    }




}



