package com.tassiecomp.mychronology.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.util.CreateSubjectDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
//    lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = CreateSubjectDialogFragment()
//        viewModel = (activity as MainActivity).viewModel

        create_new_subject.setOnClickListener() {
            showDialog()

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



