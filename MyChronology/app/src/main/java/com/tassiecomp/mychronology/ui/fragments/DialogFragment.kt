package com.tassiecomp.mychronology.util


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.db.HomeGradeDB
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.repository.MainRepository
import com.tassiecomp.mychronology.ui.MainViewModel
import com.tassiecomp.mychronology.ui.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.create_subject_dialog.*
import kotlinx.android.synthetic.main.create_subject_dialog.view.*


class CreateSubjectDialogFragment() : DialogFragment() {
    lateinit var viewModel: MainViewModel
    lateinit var myNumberViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mDialogView = inflater.inflate(R.layout.create_subject_dialog, container, false)

        mDialogView.topAppBar.setNavigationOnClickListener {
            dismiss()
        }

        mDialogView.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save -> {
                    val db = HomeGradeDB.createDatabase(App.instance)
                    db.getGradeDao().insert(HomeGrade(
                        Dialog_cardTitle.text.toString(),
                        Dialog_description.text.toString(),
                        Dialog_weighing.text.toString(),
                        0.0,
                        Dialog_min.text.toString().toDouble(),
                        Dialog_max.text.toString().toDouble(),
                    ))

                    dismiss()
                    Toast.makeText(App.instance, "Saved", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }
        return mDialogView


    }


    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)

        //unclickable background
        create_subject_layout.setOnClickListener {

        }


        return dialog


    }


}