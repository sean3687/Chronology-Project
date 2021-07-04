package com.tassiecomp.mychronology.util


import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.tassiecomp.mychronology.R
import kotlinx.android.synthetic.main.create_subject_dialog.*


class CreateSubjectDialogFragment() : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.create_subject_dialog, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR)
        dialog.setCanceledOnTouchOutside(false)
        topAppBar.setNavigationOnClickListener{

        }

        //unclickable background
        create_subject_layout.setOnClickListener {

        }
        return dialog


    }



}