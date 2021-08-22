package com.tassiecomp.mychronology.ui.fragments.HomeFragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.ui.MainViewModel
import dev.sasikanth.colorsheet.ColorSheet
import dev.sasikanth.colorsheet.utils.ColorSheetUtils
import kotlinx.android.synthetic.main.create_subject_dialog.*
import kotlinx.android.synthetic.main.create_subject_dialog.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateSubjectFragment : Fragment(R.layout.create_subject_dialog) {

    private lateinit var mainViewModel: MainViewModel


    private var selectedColor: Int = ColorSheet.NO_COLOR
    companion object {
        private const val COLOR_SELECTED = "selectedColor"

    }

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

        //colorSheet
        view.colorPicker_button.setOnClickListener{
            setupColorSheet()
        }


        return view
    }

    private fun setupColorSheet()  {
        val colors = resources.getIntArray(R.array.colors)
        ColorSheet().cornerRadius(8)
            .colorPicker(

                colors = colors,
                selectedColor = selectedColor,
                listener = { color ->
                    selectedColor = color
                    setColor(selectedColor)
                })
            .show(childFragmentManager)

    }


    private fun insertDataToDatabase() {

        val id = 0
        val title = Update_cardTitle.text.toString()
        val description = Update_description.text.toString()
        val weighing = Update_Weighing.text.toString()
        val max = Update_max.text
        val min = Update_min.text
        var color = colorPicker_button.text.toString()

        val onlyDate: LocalDate = LocalDate.now()
        val created: String = onlyDate.toString()

        Log.d("TAGGG", "$created")



        if(inputCheck(title,description,weighing,max!!,min!!)){
            if(color =="Pick Color"){
                color = "#c2c2c2"
                Log.d("TAGGG",color)
            }

            val subject = HomeGrade(id, title,description,weighing,Integer.parseInt(max.toString()),Integer.parseInt(min.toString()),color,created)
            mainViewModel.addSubject(subject)
            Toast.makeText(App.instance, "Saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createSubjectFragment_to_homeFragment)
        } else{
            Toast.makeText(App.instance, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(
        title: String,
        description: String,
        weighing: String,
        max: Editable,
        min: Editable,
    ):Boolean{
        return !(TextUtils.isEmpty(title)&&TextUtils.isEmpty(description)&& weighing.isEmpty() && max.isEmpty() && min.isEmpty() )
    }

    private fun setColor(@ColorInt color: Int) {
            displayColor.backgroundTintList = ColorStateList.valueOf(color)
            colorPicker_button.text = ColorSheetUtils.colorToHex(color)
    }


}