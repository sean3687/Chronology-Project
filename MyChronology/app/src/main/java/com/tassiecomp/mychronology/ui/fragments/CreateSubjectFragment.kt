package com.tassiecomp.mychronology.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.ui.MainViewModel
import kotlinx.android.synthetic.main.create_subject_dialog.*
import kotlinx.android.synthetic.main.create_subject_dialog.view.*

class CreateSubjectFragment : Fragment(R.layout.create_subject_dialog) {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.create_subject_dialog, container, false)

        mainViewModel =ViewModelProvider(this).get(MainViewModel::class.java)

        view.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_createSubjectFragment_to_homeFragment)

            Log.d("TAGG", "Close create subject clicked")
        }
        view.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save -> {
                    insertDataToDatabase()
                }
            }
            true
        }
        return view
    }

    private fun insertDataToDatabase() {

        val title = Dialog_cardTitle.text.toString()
        val description = Dialog_description.text.toString()
        val weighing = Dialog_weighing.text.toString()
        val max = Dialog_max.text
        val min = Dialog_min.text

        if(inputCheck(title,description,weighing,max!!,min!!)){
            val subject = HomeGrade(title,description,weighing,Integer.parseInt(max.toString()),Integer.parseInt(min.toString()))
            mainViewModel.addSubject(subject)
            Toast.makeText(App.instance, "Saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createSubjectFragment_to_homeFragment)
        } else{
            Toast.makeText(App.instance, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }




    }

    private fun inputCheck(title:String, description:String, weighing:String, max: Editable, min:Editable):Boolean{
        return !(TextUtils.isEmpty(title)&&TextUtils.isEmpty(description)&& weighing.isEmpty() && max.isEmpty() && min.isEmpty())
    }

}